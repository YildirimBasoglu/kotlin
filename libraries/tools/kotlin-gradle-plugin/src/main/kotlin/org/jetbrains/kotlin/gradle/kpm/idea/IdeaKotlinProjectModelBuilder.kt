@file:OptIn(ExternalVariantApi::class)

package org.jetbrains.kotlin.gradle.kpm.idea

import org.gradle.api.Project
import org.gradle.tooling.provider.model.ToolingModelBuilder
import org.jetbrains.kotlin.compilerRunner.konanHome
import org.jetbrains.kotlin.gradle.kpm.external.ExternalVariantApi
import org.jetbrains.kotlin.gradle.kpm.idea.IdeaKotlinProjectModelBuilder.*
import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.KotlinPm20ProjectExtension
import org.jetbrains.kotlin.gradle.utils.UnsafeApi
import java.io.File

internal interface IdeaKotlinProjectModelBuildingContext {
    val dependencyResolver: IdeaKotlinDependencyResolver

    companion object Empty : IdeaKotlinProjectModelBuildingContext {
        override val dependencyResolver: IdeaKotlinDependencyResolver = IdeaKotlinDependencyResolver.Empty
    }
}

interface IdeaKotlinProjectModelBuilder {

    enum class DependencyResolutionPhase {
        PreDependencyResolution,
        SourceDependencyResolution,
        BinaryDependencyResolution,
        PostDependencyResolution
    }

    enum class DependencyResolutionLevel {
        Default, Special
    }

    enum class DependencyTransformationPhase {
        PreDependencyTransformationPhase,
        FreeDependencyTransformationPhase,
        DependencyFilteringPhase,
        PostDependencyTransformationPhase
    }

    @ExternalVariantApi
    fun registerDependencyResolver(
        resolver: IdeaKotlinDependencyResolver,
        constraint: KotlinFragmentConstraint,
        phase: DependencyResolutionPhase,
        level: DependencyResolutionLevel = DependencyResolutionLevel.Default,
    )

    @ExternalVariantApi
    fun registerDependencyTransformer(
        transformer: IdeaKotlinDependencyTransformer,
        constraint: KotlinFragmentConstraint,
        phase: DependencyTransformationPhase
    )

    @ExternalVariantApi
    fun registerDependencyEffect(
        effect: IdeaKotlinDependencyEffect,
        constraint: KotlinFragmentConstraint
    )

    fun buildIdeaKotlinProjectModel(): IdeaKotlinProjectModel

    companion object
}

internal class IdeaKotlinProjectModelBuilderImpl @UnsafeApi("Use factory methods instead") constructor(
    private val extension: KotlinPm20ProjectExtension,
) : ToolingModelBuilder, IdeaKotlinProjectModelBuilder {

    private data class RegisteredDependencyResolver(
        val resolver: IdeaKotlinDependencyResolver,
        val constraint: KotlinFragmentConstraint,
        val phase: DependencyResolutionPhase,
        val level: DependencyResolutionLevel,
    )

    private data class RegisteredDependencyTransformer(
        val transformer: IdeaKotlinDependencyTransformer,
        val constraint: KotlinFragmentConstraint,
        val phase: DependencyTransformationPhase
    )

    private data class RegisteredDependencyEffect(
        val effect: IdeaKotlinDependencyEffect,
        val constraint: KotlinFragmentConstraint,
    )

    private val registeredDependencyResolvers = mutableListOf<RegisteredDependencyResolver>()
    private val registeredDependencyTransformers = mutableListOf<RegisteredDependencyTransformer>()
    private val registeredDependencyEffects = mutableListOf<RegisteredDependencyEffect>()

    override fun registerDependencyResolver(
        resolver: IdeaKotlinDependencyResolver,
        constraint: KotlinFragmentConstraint,
        phase: DependencyResolutionPhase,
        level: DependencyResolutionLevel
    ) {
        registeredDependencyResolvers.add(
            RegisteredDependencyResolver(resolver, constraint, phase, level)
        )
    }

    override fun registerDependencyTransformer(
        transformer: IdeaKotlinDependencyTransformer,
        constraint: KotlinFragmentConstraint,
        phase: DependencyTransformationPhase
    ) {
        registeredDependencyTransformers.add(
            RegisteredDependencyTransformer(transformer, constraint, phase)
        )
    }

    override fun registerDependencyEffect(
        effect: IdeaKotlinDependencyEffect,
        constraint: KotlinFragmentConstraint
    ) {
        registeredDependencyEffects.add(
            RegisteredDependencyEffect(effect, constraint)
        )
    }

    override fun buildIdeaKotlinProjectModel(): IdeaKotlinProjectModel {
        return Context().toIdeaKotlinProjectModel(extension)
    }

    override fun canBuild(modelName: String): Boolean =
        modelName == IdeaKotlinProjectModel::class.java.name

    override fun buildAll(modelName: String, project: Project): IdeaKotlinProjectModel {
        check(project === extension.project) { "Expected project ${extension.project.path}, found ${project.path}" }
        return buildIdeaKotlinProjectModel()
    }

    private inner class Context : IdeaKotlinProjectModelBuildingContext {
        override val dependencyResolver = createDependencyResolver()
    }

    private fun createDependencyResolver(): IdeaKotlinDependencyResolver {
        return IdeaKotlinDependencyResolver(DependencyResolutionPhase.values().map { phase ->
            createDependencyResolver(phase)
        }).withTransformer(createDependencyTransformer())
            .withEffect(createDependencyEffect())
    }

    private fun createDependencyResolver(phase: DependencyResolutionPhase) = IdeaKotlinDependencyResolver resolve@{ fragment ->
        val applicableResolvers = registeredDependencyResolvers
            .filter { it.phase == phase }
            .filter { it.constraint(fragment) }
            .groupBy { it.level }

        /* Find resolvers in the highest resolution level and only consider those */
        DependencyResolutionLevel.values().reversed().forEach { level ->
            val resolvers = applicableResolvers[level].orEmpty().map { it.resolver }
            if (resolvers.isNotEmpty()) {
                return@resolve IdeaKotlinDependencyResolver(resolvers).resolve(fragment)
            }
        }

        /* No resolvers found */
        emptySet()
    }

    private fun createDependencyTransformer(): IdeaKotlinDependencyTransformer {
        return IdeaKotlinDependencyTransformer(DependencyTransformationPhase.values().map { phase ->
            createDependencyTransformer(phase)
        })
    }

    private fun createDependencyTransformer(phase: DependencyTransformationPhase): IdeaKotlinDependencyTransformer {
        return IdeaKotlinDependencyTransformer { fragment, dependencies ->
            IdeaKotlinDependencyTransformer(
                registeredDependencyTransformers
                    .filter { it.phase == phase }
                    .filter { it.constraint(fragment) }
                    .map { it.transformer }
            ).transform(fragment, dependencies)
        }
    }

    private fun createDependencyEffect(): IdeaKotlinDependencyEffect = IdeaKotlinDependencyEffect { fragment, dependencies ->
        registeredDependencyEffects
            .filter { it.constraint(fragment) }
            .forEach { it.effect(fragment, dependencies) }
    }
}

internal fun IdeaKotlinProjectModelBuildingContext.toIdeaKotlinProjectModel(extension: KotlinPm20ProjectExtension): IdeaKotlinProjectModel {
    return IdeaKotlinProjectModelImpl(
        gradlePluginVersion = extension.project.getKotlinPluginVersion(),
        coreLibrariesVersion = extension.coreLibrariesVersion,
        explicitApiModeCliOption = extension.explicitApi?.cliOption,
        kotlinNativeHome = File(extension.project.konanHome).absoluteFile,
        modules = extension.modules.map { module -> toIdeaKotlinModule(module) }
    )
}
