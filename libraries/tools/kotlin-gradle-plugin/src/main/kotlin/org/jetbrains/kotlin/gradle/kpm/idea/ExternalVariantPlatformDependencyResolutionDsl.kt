/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

@file:OptIn(ExternalVariantApi::class)

package org.jetbrains.kotlin.gradle.kpm.idea

import org.jetbrains.kotlin.gradle.kpm.external.ExternalVariantApi
import org.jetbrains.kotlin.gradle.kpm.idea.IdeaKotlinProjectModelBuilder.*
import org.jetbrains.kotlin.gradle.kpm.idea.IdeaKotlinProjectModelBuilder.DependencyResolutionPhase.*
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.*
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.FragmentAttributes

@DslMarker
@ExternalVariantApi
annotation class ExternalVariantPlatformDependencyResolutionDsl

@ExternalVariantApi
fun KotlinPm20ProjectExtension.configureIdeaPlatformDependencyResolution(
    configure: IdeaKotlinPlatformDependencyResolutionDslHandle.() -> Unit
) {
    IdeaKotlinPlatformDependencyResolutionDslHandle(ideaKotlinProjectModelBuilder).configure()
}

@ExternalVariantPlatformDependencyResolutionDsl
class IdeaKotlinPlatformDependencyResolutionDslHandle(
    private val toolingModelBuilder: IdeaKotlinProjectModelBuilder,
    private val constraint: FragmentConstraint = FragmentConstraint.unconstrained
) {
    @ExternalVariantPlatformDependencyResolutionDsl
    class VariantDslHandle {

        @ExternalVariantPlatformDependencyResolutionDsl
        var variantBinaryType: String = IdeaKotlinDependency.CLASSPATH_BINARY_TYPE

        @ExternalVariantPlatformDependencyResolutionDsl
        var variantAttributes: FragmentAttributes<KotlinGradleFragment> = FragmentAttributes { }

        @ExternalVariantPlatformDependencyResolutionDsl
        fun variantAttributes(setAttributes: KotlinGradleFragmentConfigurationAttributesContext<KotlinGradleFragment>.() -> Unit) {
            variantAttributes += FragmentAttributes(setAttributes)
        }
    }

    @ExternalVariantPlatformDependencyResolutionDsl
    fun withConstraint(
        constraint: FragmentConstraint,
        configure: IdeaKotlinPlatformDependencyResolutionDslHandle.() -> Unit
    ) {
        IdeaKotlinPlatformDependencyResolutionDslHandle(
            toolingModelBuilder, this.constraint and constraint
        ).configure()
    }


    @ExternalVariantPlatformDependencyResolutionDsl
    fun variant(configure: VariantDslHandle.() -> Unit) {
        val variant = VariantDslHandle().apply(configure)

        /*
        toolingModelBuilder.registerDependencyResolver(
            resolver = IdeaKotlinPlatformDependencyResolver(
                binaryType = variant.variantBinaryType,
                artifactViewAttributes = variant.variantAttributes
            ),
            phase = BinaryDependencyResolution,
            constraint = constraint,
            level = DependencyResolutionLevel.Special
        )

         */
    }
}


