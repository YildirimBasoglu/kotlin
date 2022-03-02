/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle.kpm.idea

import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeCompilation
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinVariant
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.*
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.variantsContainingFragment

fun interface IdeaKotlinDependencyResolverConstraint {
    fun isApplicable(fragment: KotlinGradleFragment): Boolean

    operator fun invoke(fragment: KotlinGradleFragment) = isApplicable(fragment)

    object None : IdeaKotlinDependencyResolverConstraint {
        override fun isApplicable(fragment: KotlinGradleFragment): Boolean = true
    }

    companion object {
        val unconstrained = IdeaKotlinDependencyResolverConstraint { true }

        val isVariant = IdeaKotlinDependencyResolverConstraint { fragment -> fragment is KotlinVariant }

        val isSinglePlatformTypeFragment = isVariant or IdeaKotlinDependencyResolverConstraint { fragment ->
            val variants = fragment.containingModule.variantsContainingFragment(fragment)
            val platformTypes = variants.map { it.platformType }.toSet()
            platformTypes.size == 1
        }

        val isMultiplePlatformTypeFragment = !isVariant and IdeaKotlinDependencyResolverConstraint constraint@{ fragment ->
            val variants = fragment.containingModule.variantsContainingFragment(fragment)
            val platformTypes = variants.map { it.platformType }.toSet()
            platformTypes.size > 1
        }

        val isSharedNativeFragment = !isVariant and IdeaKotlinDependencyResolverConstraint constraint@{ fragment ->
            val konanTargets = fragment.containingModule.variantsContainingFragment(fragment).map { variant ->
                when (variant) {
                    is KotlinNativeVariantInternal -> variant.konanTarget
                    is LegacyMappedVariant -> when (val compilation = variant.compilation) {
                        is KotlinNativeCompilation -> compilation.konanTarget
                        /* Not a native compilation -> can't be shared native */
                        else -> return@constraint false
                    }

                    /* Contained in non-native variant -> can't be shared native */
                    else -> return@constraint false
                }
            }

            konanTargets.toSet().size > 1
        }
    }
}

class IdeaKotlinConstrainedDependencyResolver internal constructor(
    private val resolver: IdeaKotlinDependencyResolver,
    internal val constraint: IdeaKotlinDependencyResolverConstraint
) : IdeaKotlinDependencyResolver {
    override fun resolve(fragment: KotlinGradleFragment): Set<IdeaKotlinDependency> {
        return if (constraint.isApplicable(fragment)) resolver.resolve(fragment)
        else emptySet()
    }

    fun withConstraint(constraint: IdeaKotlinDependencyResolverConstraint): IdeaKotlinConstrainedDependencyResolver {
        return IdeaKotlinConstrainedDependencyResolver(resolver, this.constraint and constraint)
    }
}

fun IdeaKotlinDependencyResolver.withConstraint(constraint: IdeaKotlinDependencyResolverConstraint):
        IdeaKotlinConstrainedDependencyResolver {
    return if (this is IdeaKotlinConstrainedDependencyResolver) this.withConstraint(constraint)
    else IdeaKotlinConstrainedDependencyResolver(this, constraint)
}

fun IdeaKotlinDependencyResolver.withConstraint(constraint: (KotlinGradleFragment) -> Boolean):
        IdeaKotlinConstrainedDependencyResolver = withConstraint(IdeaKotlinDependencyResolverConstraint(constraint))

infix fun IdeaKotlinDependencyResolverConstraint.or(
    other: IdeaKotlinDependencyResolverConstraint
) = IdeaKotlinDependencyResolverConstraint { fragment ->
    this@or.isApplicable(fragment) || other.isApplicable(fragment)
}

infix fun IdeaKotlinDependencyResolverConstraint.and(
    other: IdeaKotlinDependencyResolverConstraint
): IdeaKotlinDependencyResolverConstraint = IdeaKotlinDependencyResolverConstraint { fragment ->
    this@and.isApplicable(fragment) && other.isApplicable(fragment)
}

operator fun IdeaKotlinDependencyResolverConstraint.not() = IdeaKotlinDependencyResolverConstraint { fragment ->
    this@not.isApplicable(fragment).not()
}
