/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

@file:OptIn(ExternalVariantApi::class)

package org.jetbrains.kotlin.gradle.kpm.idea

import org.jetbrains.kotlin.gradle.kpm.external.ExternalVariantApi
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.FragmentGranularMetadataResolverFactory
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.KotlinPm20ProjectExtension
import org.jetbrains.kotlin.gradle.utils.UnsafeApi

@OptIn(UnsafeApi::class)
internal fun IdeaKotlinProjectModelBuilder.Companion.default(
    extension: KotlinPm20ProjectExtension
) = IdeaKotlinProjectModelBuilderImpl(extension).apply {
    val fragmentMetadataResolverFactory = FragmentGranularMetadataResolverFactory()

    registerDependencyResolver(
        resolver = IdeaKotlinSourceDependencyResolver(fragmentMetadataResolverFactory),
        constraint = IdeaKotlinDependencyResolverConstraint.unconstrained,
        phase = IdeaKotlinProjectModelBuilder.DependencyResolutionPhase.SourceDependencyResolution,
        priority = IdeaKotlinProjectModelBuilder.DependencyResolverPriority.Medium,
        mode = IdeaKotlinProjectModelBuilder.DependencyResolverMode.Collaborative
    )

    registerDependencyResolver(
        resolver = IdeaKotlinMetadataBinaryDependencyResolver(fragmentMetadataResolverFactory),
        constraint = !IdeaKotlinDependencyResolverConstraint.isVariant,
        phase = IdeaKotlinProjectModelBuilder.DependencyResolutionPhase.MetadataBinaryResolution,
        priority = IdeaKotlinProjectModelBuilder.DependencyResolverPriority.Medium,
        mode = IdeaKotlinProjectModelBuilder.DependencyResolverMode.Collaborative
    )

    registerDependencyResolver(
        resolver = IdeaKotlinOriginalMetadataDependencyResolver(fragmentMetadataResolverFactory),
        constraint = !IdeaKotlinDependencyResolverConstraint.isVariant,
        phase = IdeaKotlinProjectModelBuilder.DependencyResolutionPhase.MetadataBinaryResolution,
        priority = IdeaKotlinProjectModelBuilder.DependencyResolverPriority.Medium,
        mode = IdeaKotlinProjectModelBuilder.DependencyResolverMode.Collaborative
    )

    registerDependencyResolver(
        resolver = IdeaKotlinPlatformDependencyResolver(),
        constraint = IdeaKotlinDependencyResolverConstraint.isVariant,
        phase = IdeaKotlinProjectModelBuilder.DependencyResolutionPhase.PlatformBinaryResolution,
        priority = IdeaKotlinProjectModelBuilder.DependencyResolverPriority.Medium,
        mode = IdeaKotlinProjectModelBuilder.DependencyResolverMode.Collaborative
    )

    registerDependencyResolver(
        resolver = IdeaKotlinSourcesAndDocumentationResolver(),
        constraint = IdeaKotlinDependencyResolverConstraint.unconstrained,
        phase = IdeaKotlinProjectModelBuilder.DependencyResolutionPhase.PostDependencyResolution,
        priority = IdeaKotlinProjectModelBuilder.DependencyResolverPriority.Medium,
        mode = IdeaKotlinProjectModelBuilder.DependencyResolverMode.Collaborative
    )

    registerDependencyTransformer(
        transformer = IdeaKotlinSinglePlatformStdlibCommonFilter,
        phase = IdeaKotlinProjectModelBuilder.DependencyTransformationPhase.DependencyFilteringPhase
    )

    registerDependencyTransformer(
        transformer = IdeaKotlinUnusedSourcesAndDocumentationFilter,
        phase = IdeaKotlinProjectModelBuilder.DependencyTransformationPhase.DependencyFilteringPhase
    )

    registerDependencyTransformer(
        transformer = IdeaKotlinDependencyLogger,
        phase = IdeaKotlinProjectModelBuilder.DependencyTransformationPhase.PostDependencyTransformationPhase
    )
}
