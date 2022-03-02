/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle.kpm.idea

import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.ResolvableDependencies
import org.gradle.api.artifacts.component.ComponentIdentifier
import org.gradle.api.artifacts.component.ModuleComponentIdentifier
import org.gradle.api.artifacts.component.ModuleComponentSelector
import org.gradle.api.artifacts.component.ProjectComponentIdentifier
import org.gradle.internal.resolve.ModuleVersionResolveException
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.*
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.FragmentAttributes

class IdeaKotlinPlatformDependencyResolver(
    private val binaryType: String = IdeaKotlinDependency.CLASSPATH_BINARY_TYPE,
    private val artifactViewAttributes: FragmentAttributes<KotlinGradleFragment> = FragmentAttributes { }
) : IdeaKotlinDependencyResolver {

    override fun resolve(fragment: KotlinGradleFragment): Set<IdeaKotlinBinaryDependency> {
        val artifacts = resolvableDependencies(fragment).artifactView { view ->
            artifactViewAttributes.setAttributes(view.attributes, fragment)
            view.isLenient = true
            view.componentFilter { id -> id !is ProjectComponentIdentifier }
        }.artifacts

        val unresolvedDependencies = artifacts.failures
            .onEach { reason -> fragment.project.logger.error("Failed to resolve dependency", reason) }
            .map { reason ->
                val selector = (reason as? ModuleVersionResolveException)?.selector as? ModuleComponentSelector
                /* Can't figure out the dependency here :( */
                    ?: return@map IdeaKotlinUnresolvedBinaryDependencyImpl(
                        coordinates = null, cause = reason.message?.takeIf { it.isNotBlank() }
                    )

                IdeaKotlinUnresolvedBinaryDependencyImpl(
                    coordinates = IdeaKotlinBinaryCoordinatesImpl(selector.group, selector.module, selector.version),
                    cause = reason.message?.takeIf { it.isNotBlank() }
                )
            }.toSet()

        val resolvedDependencies = artifacts.artifacts.mapNotNull { artifact ->
            IdeaKotlinResolvedBinaryDependencyImpl(
                coordinates = artifact.variant.owner.ideaKotlinBinaryCoordinates,
                binaryType = binaryType,
                binaryFile = artifact.file
            )
        }.toSet()

        return resolvedDependencies + unresolvedDependencies
    }

    private fun resolvableDependencies(fragment: KotlinGradleFragment): ResolvableDependencies {
        if (fragment is KotlinGradleVariant) {
            return fragment.compileDependenciesConfiguration.incoming
        }

        /* Resolving platform dependencies for this 'abstract' fragment */

        val fragmentCompileDependencies = fragment.project.configurations.detachedConfiguration()

        fragmentCompileDependencies.dependencies.addAll(
            fragment.transitiveApiConfiguration.allDependencies.filter { it !is ProjectDependency }
        )

        fragmentCompileDependencies.dependencies.addAll(
            fragment.transitiveImplementationConfiguration.allDependencies.filter { it !is ProjectDependency }
        )

        artifactViewAttributes.setAttributes(fragmentCompileDependencies.attributes, fragment)

        val allModuleCompileDependencies =
            fragment.project.configurations.getByName(fragment.containingModule.resolvableMetadataConfigurationName)
        fragmentCompileDependencies.shouldResolveConsistentlyWith(allModuleCompileDependencies)


        return fragmentCompileDependencies.incoming
    }
}

private val ComponentIdentifier.ideaKotlinBinaryCoordinates: IdeaKotlinBinaryCoordinates?
    get() = when (this) {
        is ModuleComponentIdentifier -> IdeaKotlinBinaryCoordinatesImpl(group, module, version)
        else -> null
    }
