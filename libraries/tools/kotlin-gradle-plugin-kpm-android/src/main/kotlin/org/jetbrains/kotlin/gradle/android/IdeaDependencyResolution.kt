/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle.android

import com.android.build.gradle.internal.publishing.AndroidArtifacts
import org.gradle.api.attributes.Usage
import org.jetbrains.kotlin.gradle.kpm.external.ExternalVariantApi
import org.jetbrains.kotlin.gradle.kpm.external.external
import org.jetbrains.kotlin.gradle.kpm.external.ideaKotlinProjectModelBuilder
import org.jetbrains.kotlin.gradle.kpm.external.project
import org.jetbrains.kotlin.gradle.kpm.idea.*
import org.jetbrains.kotlin.gradle.kpm.idea.IdeaKotlinDependency.Companion.CLASSPATH_BINARY_TYPE
import org.jetbrains.kotlin.gradle.kpm.idea.IdeaKotlinProjectModelBuilder.FragmentConstraint
import org.jetbrains.kotlin.gradle.kpm.idea.IdeaKotlinProjectModelBuilder.FragmentConstraint.Companion.isVariant
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.FragmentAttributes
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.KotlinPm20ProjectExtension

@OptIn(ExternalVariantApi::class, InternalKotlinGradlePluginApi::class)
internal fun KotlinPm20ProjectExtension.setupIdeaKotlinFragmentDependencyResolver() {

    configureIdeaPlatformDependencyResolution {
        withConstraint({ fragment -> androidDslKey in fragment.external }) {
            variant {
                variantBinaryType = CLASSPATH_BINARY_TYPE
                variantAttributes {
                    attribute(AndroidArtifacts.ARTIFACT_TYPE, AndroidArtifacts.ArtifactType.CLASSES_JAR.type)
                }
            }

            variant {
                variantBinaryType = "manifest"
                variantAttributes {
                    attribute(AndroidArtifacts.ARTIFACT_TYPE, AndroidArtifacts.ArtifactType.MANIFEST.type)
                }
            }

            variant {
                variantBinaryType = "resources"
                variantAttributes {
                    attribute(AndroidArtifacts.ARTIFACT_TYPE, AndroidArtifacts.ArtifactType.ANDROID_RES.type)
                }
            }

            variant {
                variantBinaryType = "android-symbol"
                variantAttributes {
                    attribute(AndroidArtifacts.ARTIFACT_TYPE, AndroidArtifacts.ArtifactType.COMPILE_SYMBOL_LIST.type)
                }
            }
        }
    }

    ideaKotlinProjectModelBuilder.registerDependencyResolver(
        IdeaKotlinPlatformDependencyResolver(
            binaryType = CLASSPATH_BINARY_TYPE,
            artifactResolution = IdeaKotlinPlatformDependencyResolver.ArtifactResolution.Variant(FragmentAttributes {
                attribute(AndroidArtifacts.ARTIFACT_TYPE, AndroidArtifacts.ArtifactType.CLASSES_JAR.type)
            })
        ),
        constraint = isVariant and FragmentConstraint { androidDslKey in it.external },
        phase = IdeaKotlinProjectModelBuilder.DependencyResolutionPhase.BinaryDependencyResolution,
        level = IdeaKotlinProjectModelBuilder.DependencyResolutionLevel.Special
    )

    ideaKotlinProjectModelBuilder.registerDependencyResolver(
        IdeaKotlinPlatformDependencyResolver(
            binaryType = "manifest",
            artifactResolution = IdeaKotlinPlatformDependencyResolver.ArtifactResolution.Variant(FragmentAttributes {
                attribute(AndroidArtifacts.ARTIFACT_TYPE, AndroidArtifacts.ArtifactType.MANIFEST.type)
            })
        ),
        constraint = isVariant and FragmentConstraint { androidDslKey in it.external },
        phase = IdeaKotlinProjectModelBuilder.DependencyResolutionPhase.BinaryDependencyResolution,
        level = IdeaKotlinProjectModelBuilder.DependencyResolutionLevel.Special
    )

    ideaKotlinProjectModelBuilder.registerDependencyResolver(
        IdeaKotlinPlatformDependencyResolver(
            binaryType = "android-symbol",
            artifactResolution = IdeaKotlinPlatformDependencyResolver.ArtifactResolution.Variant(FragmentAttributes {
                attribute(AndroidArtifacts.ARTIFACT_TYPE, AndroidArtifacts.ArtifactType.COMPILE_SYMBOL_LIST.type)
            })
        ),
        constraint = isVariant and FragmentConstraint { androidDslKey in it.external },
        phase = IdeaKotlinProjectModelBuilder.DependencyResolutionPhase.BinaryDependencyResolution,
        level = IdeaKotlinProjectModelBuilder.DependencyResolutionLevel.Special
    )

    /* Resolver for 'platform fragments' */
    ideaKotlinProjectModelBuilder.registerDependencyResolver(
        IdeaKotlinPlatformDependencyResolver(
            binaryType = CLASSPATH_BINARY_TYPE,
            artifactResolution = IdeaKotlinPlatformDependencyResolver.ArtifactResolution.PlatformFragment(
                platformResolutionAttributes = FragmentAttributes {
                    namedAttribute(Usage.USAGE_ATTRIBUTE, Usage.JAVA_API)
                    attribute(KotlinPlatformType.attribute, KotlinPlatformType.androidJvm)
                },
                artifactViewAttributes = FragmentAttributes {
                    attribute(AndroidArtifacts.ARTIFACT_TYPE, AndroidArtifacts.ArtifactType.CLASSES_JAR.type)
                }
            )
        ),
        constraint = !isVariant and FragmentConstraint { androidDslKey in it.external },
        phase = IdeaKotlinProjectModelBuilder.DependencyResolutionPhase.BinaryDependencyResolution,
        level = IdeaKotlinProjectModelBuilder.DependencyResolutionLevel.Special
    )

    ideaKotlinProjectModelBuilder.registerDependencyResolver(
        IdeaKotlinPlatformDependencyResolver(
            binaryType = "manifest",
            artifactResolution = IdeaKotlinPlatformDependencyResolver.ArtifactResolution.PlatformFragment(
                platformResolutionAttributes = FragmentAttributes {
                    namedAttribute(Usage.USAGE_ATTRIBUTE, Usage.JAVA_API)
                    attribute(KotlinPlatformType.attribute, KotlinPlatformType.androidJvm)
                },
                artifactViewAttributes = FragmentAttributes {
                    attribute(AndroidArtifacts.ARTIFACT_TYPE, AndroidArtifacts.ArtifactType.MANIFEST.type)
                }
            )
        ),
        constraint = !isVariant and FragmentConstraint { androidDslKey in it.external },
        phase = IdeaKotlinProjectModelBuilder.DependencyResolutionPhase.BinaryDependencyResolution,
        level = IdeaKotlinProjectModelBuilder.DependencyResolutionLevel.Special
    )

    ideaKotlinProjectModelBuilder.registerDependencyResolver(
        IdeaKotlinPlatformDependencyResolver(
            binaryType = "android-symbol",
            artifactResolution = IdeaKotlinPlatformDependencyResolver.ArtifactResolution.PlatformFragment(
                platformResolutionAttributes = FragmentAttributes {
                    namedAttribute(Usage.USAGE_ATTRIBUTE, Usage.JAVA_API)
                    attribute(KotlinPlatformType.attribute, KotlinPlatformType.androidJvm)
                },
                artifactViewAttributes = FragmentAttributes {
                    attribute(AndroidArtifacts.ARTIFACT_TYPE, AndroidArtifacts.ArtifactType.COMPILE_SYMBOL_LIST.type)
                }
            )
        ),
        constraint = !isVariant and FragmentConstraint { androidDslKey in it.external },
        phase = IdeaKotlinProjectModelBuilder.DependencyResolutionPhase.BinaryDependencyResolution,
        level = IdeaKotlinProjectModelBuilder.DependencyResolutionLevel.Special
    )


    ideaKotlinProjectModelBuilder.registerDependencyResolver(
        IdeaKotlinDependencyResolver resolve@{
            project.getAndroidRuntimeJars().map { androidRuntimeJar ->
                IdeaKotlinResolvedBinaryDependencyImpl(
                    binaryType = CLASSPATH_BINARY_TYPE,
                    binaryFile = androidRuntimeJar,
                    coordinates = null
                )
            }.toSet()
        },
        constraint = { androidDslKey in it.external },
        phase = IdeaKotlinProjectModelBuilder.DependencyResolutionPhase.BinaryDependencyResolution,
        level = IdeaKotlinProjectModelBuilder.DependencyResolutionLevel.Special
    )
}
