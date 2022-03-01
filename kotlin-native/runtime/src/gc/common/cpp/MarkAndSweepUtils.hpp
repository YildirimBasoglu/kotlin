/*
 * Copyright 2010-2021 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#ifndef RUNTIME_GC_COMMON_MARK_AND_SWEEP_UTILS_H
#define RUNTIME_GC_COMMON_MARK_AND_SWEEP_UTILS_H

#include "ExtraObjectData.hpp"
#include "FinalizerHooks.hpp"
#include "GlobalData.hpp"
#include "Logging.hpp"
#include "Memory.h"
#include "ObjectOps.hpp"
#include "ObjectTraversal.hpp"
#include "RootSet.hpp"
#include "Runtime.h"
#include "StableRefRegistry.hpp"
#include "ThreadData.hpp"
#include "Types.h"

namespace kotlin {
namespace gc {

// This implementation of mark queue may allocates memory during collection.
struct VectorMarkQueueTraits {
    using Type = KStdVector<ObjHeader*>;

    static bool empty(const Type& queue) noexcept { return queue.empty(); }

    static void clear(Type& queue) noexcept { queue.clear(); }

    static ObjHeader* pop(Type& queue) noexcept {
        auto top = queue.back();
        queue.pop_back();
        return top;
    }

    static void push(Type& queue, ObjHeader* item) noexcept { queue.push_back(item); }
};

struct MarkStats {
    // How many objects are alive.
    size_t aliveHeapSet = 0;
    // How many objects are alive in bytes. Note: this does not include overhead of malloc/mimalloc itself.
    size_t aliveHeapSetBytes = 0;
    // How many times a marked object was found in the mark queue.
    size_t duplicateEntries = 0;
};

template <typename Traits>
MarkStats Mark(typename Traits::MarkQueue::Type& graySet) noexcept {
    MarkStats stats;
    while (!Traits::MarkQueue::empty(graySet)) {
        ObjHeader* top = Traits::MarkQueue::pop(graySet);

        RuntimeAssert(!isNullOrMarker(top), "Got invalid reference %p in gray set", top);
        RuntimeAssert(top->heap(), "Got non-heap reference %p in gray set, permanent=%d stack=%d", top, top->permanent(), top->local());

        if (!Traits::TryMark(top)) {
            ++stats.duplicateEntries;
            continue;
        }
        stats.aliveHeapSet++;
        stats.aliveHeapSetBytes += mm::GetAllocatedHeapSize(top);

        traverseReferredObjects(top, [&](ObjHeader* field) noexcept {
            if (!isNullOrMarker(field) && field->heap() && !Traits::IsMarked(field)) {
                Traits::MarkQueue::push(graySet, field);
            }
        });

        if (auto* extraObjectData = mm::ExtraObjectData::Get(top)) {
            auto weakCounter = extraObjectData->GetWeakReferenceCounter();
            if (!isNullOrMarker(weakCounter)) {
                RuntimeAssert(
                        weakCounter->heap(), "Weak counter must be a heap object. object=%p counter=%p permanent=%d local=%d", top,
                        weakCounter, weakCounter->permanent(), weakCounter->local());
                Traits::MarkQueue::push(graySet, weakCounter);
            }
        }
    }
    return stats;
}

template <typename Traits>
void SweepExtraObjects(typename Traits::ExtraObjectsFactory& objectFactory) noexcept {
    objectFactory.ProcessDeletions();
    auto iter = objectFactory.LockForIter();
    for (auto it = iter.begin(); it != iter.end();) {
        auto &extraObject = *it;
        if (!extraObject.getFlag(mm::ExtraObjectData::FLAGS_IN_FINALIZER_QUEUE) && !Traits::IsMarkedByExtraObject(extraObject)) {
            extraObject.ClearWeakReferenceCounter();
            if (extraObject.HasAssociatedObject()) {
                extraObject.DetachAssociatedObject();
                extraObject.setFlag(mm::ExtraObjectData::FLAGS_IN_FINALIZER_QUEUE);
                ++it;
            } else {
                extraObject.Uninstall();
                objectFactory.EraseAndAdvance(it);
            }
        } else {
            ++it;
        }
    }
}

template <typename Traits>
typename Traits::ObjectFactory::FinalizerQueue Sweep(typename Traits::ObjectFactory::Iterable& objectFactoryIter) noexcept {
    typename Traits::ObjectFactory::FinalizerQueue finalizerQueue;

    for (auto it = objectFactoryIter.begin(); it != objectFactoryIter.end();) {
        if (Traits::TryResetMark(*it)) {
            ++it;
            continue;
        }
        auto* objHeader = it->IsArray() ? it->GetArrayHeader()->obj() : it->GetObjHeader();
        if (HasFinalizers(objHeader)) {
            objectFactoryIter.MoveAndAdvance(finalizerQueue, it);
        } else {
            objectFactoryIter.EraseAndAdvance(it);
        }
    }

    return finalizerQueue;
}

template <typename Traits>
typename Traits::ObjectFactory::FinalizerQueue Sweep(typename Traits::ObjectFactory& objectFactory) noexcept {
    auto iter = objectFactory.LockForIter();
    return Sweep<Traits>(iter);
}

template <typename Traits>
void collectRootSet(typename Traits::MarkQueue::Type& graySet) noexcept {
    Traits::MarkQueue::clear(graySet);
    for (auto& thread : mm::GlobalData::Instance().threadRegistry().LockForIter()) {
        // TODO: Maybe it's more efficient to do by the suspending thread?
        thread.Publish();
        thread.gc().OnStoppedForGC();
        size_t stack = 0;
        size_t tls = 0;
        for (auto value : mm::ThreadRootSet(thread)) {
            auto* object = value.object;
            if (!isNullOrMarker(object)) {
                if (object->heap()) {
                    Traits::MarkQueue::push(graySet, object);
                } else {
                    traverseReferredObjects(object, [&](ObjHeader* field) noexcept {
                        // Each permanent and stack object has own entry in the root set.
                        if (field->heap() && !isNullOrMarker(field)) {
                            Traits::MarkQueue::push(graySet, field);
                        }
                    });
                    RuntimeAssert(!object->has_meta_object(), "Non-heap object %p may not have an extra object data", object);
                }
                switch (value.source) {
                    case mm::ThreadRootSet::Source::kStack:
                        ++stack;
                        break;
                    case mm::ThreadRootSet::Source::kTLS:
                        ++tls;
                        break;
                }
            }
        }
        RuntimeLogDebug({kTagGC}, "Collected root set for thread stack=%zu tls=%zu", stack, tls);
    }
    mm::StableRefRegistry::Instance().ProcessDeletions();
    size_t global = 0;
    size_t stableRef = 0;
    for (auto value : mm::GlobalRootSet()) {
        auto* object = value.object;
        if (!isNullOrMarker(object)) {
            if (object->heap()) {
                Traits::MarkQueue::push(graySet, object);
            } else {
                traverseReferredObjects(object, [&](ObjHeader* field) noexcept {
                    // Each permanent and stack object has own entry in the root set.
                    if (field->heap() && !isNullOrMarker(field)) {
                        Traits::MarkQueue::push(graySet, field);
                    }
                });
                RuntimeAssert(!object->has_meta_object(), "Non-heap object %p may not have an extra object data", object);
            }
            switch (value.source) {
                case mm::GlobalRootSet::Source::kGlobal:
                    ++global;
                    break;
                case mm::GlobalRootSet::Source::kStableRef:
                    ++stableRef;
                    break;
            }
        }
    }
    RuntimeLogDebug({kTagGC}, "Collected global root set global=%zu stableRef=%zu", global, stableRef);
}

} // namespace gc
} // namespace kotlin

#endif // RUNTIME_GC_COMMON_MARK_AND_SWEEP_UTILS_H
