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
    private val binaryType: String = IdeaKotlinFragmentBinaryDependency.CLASSPATH_BINARY_TYPE,
    private val attributes: FragmentAttributes<KotlinGradleFragment> = FragmentAttributes { }
) : IdeaKotlinDependencyResolver {

    override fun resolve(fragment: KotlinGradleFragment): Set<IdeaKotlinFragmentBinaryDependency> {
        val artifacts = resolvableDependencies(fragment).artifactView { view ->
            attributes.setAttributes(view.attributes, fragment)
            view.isLenient = true
            view.componentFilter { id -> id !is ProjectComponentIdentifier }
        }.artifacts

        val unresolvedDependencies = artifacts.failures
            .onEach { reason -> fragment.project.logger.error("Failed to resolve dependency", reason) }
            .map { reason ->
                val selector = (reason as? ModuleVersionResolveException)?.selector as? ModuleComponentSelector
                /* Can't figure out the dependency here :( */
                    ?: return@map IdeaKotlinFragmentUnresolvedBinaryDependencyImpl(
                        coordinates = null, cause = reason.message?.takeIf { it.isNotBlank() }
                    )

                IdeaKotlinFragmentUnresolvedBinaryDependencyImpl(
                    coordinates = IdeaKotlinBinaryCoordinatesImpl(selector.group, selector.module, selector.version),
                    cause = reason.message?.takeIf { it.isNotBlank() }
                )
            }.toSet()

        val resolvedDependencies = artifacts.artifacts.mapNotNull { artifact ->
            IdeaKotlinFragmentResolvedBinaryDependencyImpl(
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

        attributes.setAttributes(fragmentCompileDependencies.attributes, fragment)

        val allModuleCompileDependencies = fragment.project.configurations.getByName(fragment.containingModule.resolvableMetadataConfigurationName)
        fragmentCompileDependencies.shouldResolveConsistentlyWith(allModuleCompileDependencies)

        /*val fragmentCompileDependenciesName = fragment.disambiguateName("transitiveCompileDependencies")
        val fragmentCompileDependencies =
            fragment.project.configurations.findByName(fragmentCompileDependenciesName)
                ?: fragment.project.configurations.create(fragmentCompileDependenciesName) { configuration ->
                    configuration.isCanBeConsumed = false
                    configuration.isCanBeResolved = true

                    /*
                    configuration.attributes.attribute(
                        BuildTypeAttr.ATTRIBUTE, fragment.project.objects.named("shared")
                    )

                     */

                    configuration.attributes.attribute(
                        TargetJvmEnvironment.TARGET_JVM_ENVIRONMENT_ATTRIBUTE,
                        fragment.project.objects.named(TargetJvmEnvironment.ANDROID)
                    )
                    configuration.attributes.attribute(Usage.USAGE_ATTRIBUTE, fragment.project.usageByName(Usage.JAVA_API))
                    configuration.attributes.attribute(KotlinPlatformType.attribute, KotlinPlatformType.androidJvm)

                    configuration.shouldResolveConsistentlyWith(moduleCompileDependencies)
                    configuration.extendsFrom(fragment.transitiveImplementationConfiguration)
                    configuration.extendsFrom(fragment.transitiveApiConfiguration)
                }

         */


        return fragmentCompileDependencies.incoming
    }
}

private val ComponentIdentifier.ideaKotlinBinaryCoordinates: IdeaKotlinBinaryCoordinates?
    get() = when (this) {
        is ModuleComponentIdentifier -> IdeaKotlinBinaryCoordinatesImpl(group, module, version)
        else -> null
    }
