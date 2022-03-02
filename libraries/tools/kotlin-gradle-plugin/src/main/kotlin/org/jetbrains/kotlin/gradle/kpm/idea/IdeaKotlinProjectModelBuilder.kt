package org.jetbrains.kotlin.gradle.kpm.idea

import org.gradle.api.Project
import org.gradle.tooling.provider.model.ToolingModelBuilder
import org.jetbrains.kotlin.compilerRunner.konanHome
import org.jetbrains.kotlin.gradle.kpm.external.ExternalVariantApi
import org.jetbrains.kotlin.gradle.kpm.idea.IdeaKotlinDependencyResolverConstraint.Companion.isSharedNativeFragment
import org.jetbrains.kotlin.gradle.kpm.idea.IdeaKotlinDependencyResolverConstraint.Companion.isSinglePlatformTypeFragment
import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.KotlinPm20ProjectExtension
import java.io.File

internal interface IdeaKotlinProjectModelBuildingContext {
    val dependencyResolver: IdeaKotlinDependencyResolver

    companion object Empty : IdeaKotlinProjectModelBuildingContext {
        override val dependencyResolver: IdeaKotlinDependencyResolver = IdeaKotlinDependencyResolver.Empty
    }
}

interface IdeaKotlinProjectModelBuilder {
    @ExternalVariantApi
    fun registerPlatformDependencyResolver(resolver: IdeaKotlinConstrainedDependencyResolver)

    @ExternalVariantApi
    fun registerMetadataDependencyResolver(resolver: IdeaKotlinConstrainedDependencyResolver)

    @ExternalVariantApi
    fun addAdditionalDependencyResolver(resolver: IdeaKotlinConstrainedDependencyResolver)

    @ExternalVariantApi
    fun registerDependencyTransformer(transformer: IdeaKotlinDependencyTransformer)

    fun buildIdeaKotlinProjectModel(): IdeaKotlinProjectModel
}

internal class IdeaKotlinProjectModelBuilderImpl(
    private val extension: KotlinPm20ProjectExtension,

    private val defaultPlatformDependencyResolver: IdeaKotlinDependencyResolver =
        IdeaKotlinPlatformDependencyResolver(),

    private val defaultMetadataDependencyResolver: IdeaKotlinDependencyResolver =
        IdeaKotlinMetadataDependencyResolver(),

    private val defaultAdditionalDependencyResolver: IdeaKotlinDependencyResolver =
        IdeaKotlinSourcesAndDocumentationResolver(),

    private val defaultDependencyTransformer: IdeaKotlinDependencyTransformer = IdeaKotlinDependencyTransformer(
        IdeaKotlinSinglePlatformStdlibCommonFilter,
        IdeaKotlinUnusedSourcesAndDocumentationFilter,
        IdeaKotlinDependencyLogger
    )

) : ToolingModelBuilder, IdeaKotlinProjectModelBuilder {

    private val registeredPlatformDependencyResolvers = mutableListOf<IdeaKotlinConstrainedDependencyResolver>()
    private val registeredMetadataDependencyResolvers = mutableListOf<IdeaKotlinConstrainedDependencyResolver>()
    private val registeredAdditionalDependencyResolvers = mutableListOf<IdeaKotlinConstrainedDependencyResolver>()
    private val registeredDependencyTransformers = mutableListOf<IdeaKotlinDependencyTransformer>()

    @ExternalVariantApi
    override fun registerPlatformDependencyResolver(
        resolver: IdeaKotlinConstrainedDependencyResolver,
    ) {
        registeredPlatformDependencyResolvers.add(resolver)
    }

    @ExternalVariantApi
    override fun registerMetadataDependencyResolver(
        resolver: IdeaKotlinConstrainedDependencyResolver,
    ) {
        registeredMetadataDependencyResolvers.add(resolver)
    }

    @ExternalVariantApi
    override fun addAdditionalDependencyResolver(resolver: IdeaKotlinConstrainedDependencyResolver) {
        registeredAdditionalDependencyResolvers.add(resolver)
    }

    @ExternalVariantApi
    override fun registerDependencyTransformer(transformer: IdeaKotlinDependencyTransformer) {
        registeredDependencyTransformers.add(transformer)
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
        override val dependencyResolver = IdeaKotlinDependencyResolver(
            createPlatformDependencyResolver(),
            createMetadataDependencyResolver(),
            createAdditionalDependencyResolver()
        ).withTransformer(createDependencyTransformer())
    }

    private fun createPlatformDependencyResolver() = IdeaKotlinDependencyResolver resolve@{ fragment ->
        if (!isSinglePlatformTypeFragment(fragment)) return@resolve emptySet()
        if (isSharedNativeFragment(fragment)) return@resolve emptySet()

        val applicableRegisteredPlatformDependencyResolvers = registeredPlatformDependencyResolvers
            .filter { resolver -> resolver.constraint(fragment) }

        /* Special resolvers were registered, that want to handle this fragment */
        if (applicableRegisteredPlatformDependencyResolvers.isNotEmpty()) {
            return@resolve IdeaKotlinDependencyResolver(applicableRegisteredPlatformDependencyResolvers)
                .resolve(fragment)
        }

        defaultPlatformDependencyResolver.resolve(fragment)
    }

    private fun createMetadataDependencyResolver() = IdeaKotlinDependencyResolver resolve@{ fragment ->
        /* 'Platform like' fragments shall resolve platform dependencies over metadata */
        if (isSinglePlatformTypeFragment(fragment) && !isSharedNativeFragment(fragment)) return@resolve emptySet()

        val applicableRegisteredMetadataDependencyResolvers = registeredMetadataDependencyResolvers
            .filter { resolver -> resolver.constraint(fragment) }

        /* Special resolvers were registered that want to handle this fragment */
        if (applicableRegisteredMetadataDependencyResolvers.isNotEmpty()) {
            return@resolve IdeaKotlinDependencyResolver(applicableRegisteredMetadataDependencyResolvers)
                .resolve(fragment)
        }

        defaultMetadataDependencyResolver.resolve(fragment)
    }

    private fun createAdditionalDependencyResolver() =
        IdeaKotlinDependencyResolver(registeredAdditionalDependencyResolvers + defaultAdditionalDependencyResolver)

    private fun createDependencyTransformer() =
        IdeaKotlinDependencyTransformer(registeredDependencyTransformers + defaultDependencyTransformer)
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
