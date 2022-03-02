@file:OptIn(ExternalVariantApi::class)

package org.jetbrains.kotlin.gradle.kpm.idea

import org.gradle.api.Project
import org.gradle.tooling.provider.model.ToolingModelBuilder
import org.jetbrains.kotlin.compilerRunner.konanHome
import org.jetbrains.kotlin.gradle.kpm.external.ExternalVariantApi
import org.jetbrains.kotlin.gradle.kpm.idea.IdeaKotlinDependencyResolverConstraint.Companion.isSharedNativeFragment
import org.jetbrains.kotlin.gradle.kpm.idea.IdeaKotlinDependencyResolverConstraint.Companion.isSinglePlatformTypeFragment
import org.jetbrains.kotlin.gradle.kpm.idea.IdeaKotlinDependencyResolverConstraint.Companion.isVariant
import org.jetbrains.kotlin.gradle.kpm.idea.IdeaKotlinDependencyResolverConstraint.Companion.unconstrained
import org.jetbrains.kotlin.gradle.kpm.idea.IdeaKotlinProjectModelBuilder.*
import org.jetbrains.kotlin.gradle.kpm.idea.IdeaKotlinProjectModelBuilder.DependencyResolutionPhase.*
import org.jetbrains.kotlin.gradle.kpm.idea.IdeaKotlinProjectModelBuilder.DependencyResolverMode.Collaborative
import org.jetbrains.kotlin.gradle.kpm.idea.IdeaKotlinProjectModelBuilder.DependencyResolverMode.Terminal
import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.FragmentGranularMetadataResolverFactory
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
        MetadataBinaryResolution,
        PlatformBinaryResolution,
        PostDependencyResolution
    }

    enum class DependencyResolverPriority {
        Low, Medium, High
    }

    enum class DependencyResolverMode {
        Collaborative,
        Terminal
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
        constraint: IdeaKotlinDependencyResolverConstraint,
        phase: DependencyResolutionPhase,
        priority: DependencyResolverPriority = DependencyResolverPriority.Medium,
        mode: DependencyResolverMode = Collaborative
    )

    @ExternalVariantApi
    fun registerDependencyTransformer(
        transformer: IdeaKotlinDependencyTransformer,
        phase: DependencyTransformationPhase
    )

    fun buildIdeaKotlinProjectModel(): IdeaKotlinProjectModel

    companion object
}

internal class IdeaKotlinProjectModelBuilderImpl @UnsafeApi("Use factory methods instead") constructor(
    private val extension: KotlinPm20ProjectExtension,
) : ToolingModelBuilder, IdeaKotlinProjectModelBuilder {

    private data class RegisteredDependencyResolver(
        val resolver: IdeaKotlinDependencyResolver,
        val constraint: IdeaKotlinDependencyResolverConstraint,
        val phase: DependencyResolutionPhase,
        val priority: DependencyResolverPriority,
        val mode: DependencyResolverMode
    )

    private data class RegisteredDependencyTransformer(
        val transformer: IdeaKotlinDependencyTransformer,
        val phase: DependencyTransformationPhase
    )

    private val registeredDependencyResolvers = ArrayDeque<RegisteredDependencyResolver>()
    private val registeredDependencyTransformers = ArrayDeque<RegisteredDependencyTransformer>()

    @ExternalVariantApi
    override fun registerDependencyResolver(
        resolver: IdeaKotlinDependencyResolver,
        constraint: IdeaKotlinDependencyResolverConstraint,
        phase: DependencyResolutionPhase,
        priority: DependencyResolverPriority,
        mode: DependencyResolverMode
    ) {
        registeredDependencyResolvers.addFirst(
            RegisteredDependencyResolver(resolver, constraint, phase, priority, mode)
        )
    }

    @ExternalVariantApi
    override fun registerDependencyTransformer(
        transformer: IdeaKotlinDependencyTransformer,
        phase: DependencyTransformationPhase
    ) {
        registeredDependencyTransformers.addFirst(
            RegisteredDependencyTransformer(transformer, phase)
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
            .withTransformer(createDependencyTransformer())
    }

    private fun createDependencyResolver(): IdeaKotlinDependencyResolver {
        return IdeaKotlinDependencyResolver(DependencyResolutionPhase.values().map { phase ->
            createDependencyResolver(phase)
        })
    }

    private fun createDependencyResolver(phase: DependencyResolutionPhase) = IdeaKotlinDependencyResolver resolve@{ fragment ->
        val applicableResolvers = registeredDependencyResolvers
            .filter { it.phase == phase }
            .filter { it.constraint(fragment) }
            .sortedByDescending { it.priority }

        val collaborativeResolvers = applicableResolvers.takeWhile { it.mode == Collaborative }.map { it.resolver }
        val terminalResolver = listOfNotNull(applicableResolvers.firstOrNull { it.mode == Terminal }).map { it.resolver }
        val resolvers = collaborativeResolvers + terminalResolver

        IdeaKotlinDependencyResolver(resolvers).resolve(fragment)
    }

    private fun createDependencyTransformer(): IdeaKotlinDependencyTransformer {
        return IdeaKotlinDependencyTransformer(DependencyTransformationPhase.values().map { phase ->
            createDependencyTransformer(phase)
        })
    }

    private fun createDependencyTransformer(phase: DependencyTransformationPhase): IdeaKotlinDependencyTransformer {
        return IdeaKotlinDependencyTransformer(
            registeredDependencyTransformers.filter { it.phase == phase }.map { it.transformer }
        )
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
}
