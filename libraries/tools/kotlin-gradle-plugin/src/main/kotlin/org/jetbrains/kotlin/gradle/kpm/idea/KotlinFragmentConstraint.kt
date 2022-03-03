/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle.kpm.idea

import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeCompilation
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinVariant
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.*
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.variantsContainingFragment

fun interface KotlinFragmentConstraint {
    fun isApplicable(fragment: KotlinGradleFragment): Boolean

    operator fun invoke(fragment: KotlinGradleFragment) = isApplicable(fragment)

    object None : KotlinFragmentConstraint {
        override fun isApplicable(fragment: KotlinGradleFragment): Boolean = true
    }

    companion object {
        val unconstrained = KotlinFragmentConstraint { true }

        val isVariant = KotlinFragmentConstraint { fragment -> fragment is KotlinVariant }

        val isSinglePlatformTypeFragment = isVariant or KotlinFragmentConstraint { fragment ->
            val variants = fragment.containingModule.variantsContainingFragment(fragment)
            val platformTypes = variants.map { it.platformType }.toSet()
            platformTypes.size == 1
        }

        val isMultiplePlatformTypeFragment = !isVariant and KotlinFragmentConstraint constraint@{ fragment ->
            val variants = fragment.containingModule.variantsContainingFragment(fragment)
            val platformTypes = variants.map { it.platformType }.toSet()
            platformTypes.size > 1
        }

        val isSharedNativeFragment = !isVariant and KotlinFragmentConstraint constraint@{ fragment ->
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

infix fun KotlinFragmentConstraint.or(
    other: KotlinFragmentConstraint
) = KotlinFragmentConstraint { fragment ->
    this@or.isApplicable(fragment) || other.isApplicable(fragment)
}

infix fun KotlinFragmentConstraint.and(
    other: KotlinFragmentConstraint
): KotlinFragmentConstraint = KotlinFragmentConstraint { fragment ->
    this@and.isApplicable(fragment) && other.isApplicable(fragment)
}

operator fun KotlinFragmentConstraint.not() = KotlinFragmentConstraint { fragment ->
    this@not.isApplicable(fragment).not()
}
