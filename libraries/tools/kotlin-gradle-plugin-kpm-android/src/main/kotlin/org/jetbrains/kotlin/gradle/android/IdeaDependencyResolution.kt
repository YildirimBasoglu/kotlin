/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle.android

import com.android.build.api.attributes.BuildTypeAttr
import com.android.build.gradle.internal.publishing.AndroidArtifacts
import org.gradle.api.attributes.Usage
import org.gradle.api.attributes.java.TargetJvmEnvironment
import org.gradle.api.attributes.java.TargetJvmEnvironment.TARGET_JVM_ENVIRONMENT_ATTRIBUTE
import org.gradle.kotlin.dsl.named
import org.jetbrains.kotlin.gradle.kpm.external.ExternalVariantApi
import org.jetbrains.kotlin.gradle.kpm.external.external
import org.jetbrains.kotlin.gradle.kpm.external.ideaKotlinProjectModelBuilder
import org.jetbrains.kotlin.gradle.kpm.external.project
import org.jetbrains.kotlin.gradle.kpm.idea.*
import org.jetbrains.kotlin.gradle.kpm.idea.IdeaKotlinFragmentBinaryDependency.Companion.CLASSPATH_BINARY_TYPE
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.DefaultKotlinCompileDependenciesDefinition
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.KotlinGradleVariant
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.KotlinPm20ProjectExtension

@OptIn(ExternalVariantApi::class, InternalKotlinGradlePluginApi::class)
internal fun KotlinPm20ProjectExtension.setupIdeaKotlinFragmentDependencyResolver() {
    configureIdeaPlatformDependencyResolution {
        withConstraint({ fragment -> androidDslKey in fragment.external }) {
            variant {
                variantBinaryType = CLASSPATH_BINARY_TYPE
                variantAttributes {
                    namedAttribute(TARGET_JVM_ENVIRONMENT_ATTRIBUTE, TargetJvmEnvironment.ANDROID)
                    namedAttribute(Usage.USAGE_ATTRIBUTE, Usage.JAVA_API)
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

    /*
    ideaKotlinProjectModelBuilder.registerPlatformDependencyResolver(
        IdeaKotlinDependencyResolver resolve@{
            project.getAndroidRuntimeJars().map { androidRuntimeJar ->
                IdeaKotlinFragmentResolvedBinaryDependencyImpl(
                    binaryType = CLASSPATH_BINARY_TYPE,
                    binaryFile = androidRuntimeJar,
                    coordinates = null
                )
            }.toSet()
        }.withConstraint { fragment -> fragment !is KotlinGradleVariant && androidDslKey in fragment.external }
    )

     */

    /*
    ideaKotlinProjectModelBuilder.registerDependencyResolver(
        (IdeaKotlinPlatformDependencyResolver(
            binaryType = "manifest",
            attributes = FragmentAttributes {
                attribute(AndroidArtifacts.ARTIFACT_TYPE, AndroidArtifacts.ArtifactType.MANIFEST.type)
            }
        ) + IdeaKotlinPlatformDependencyResolver(
            binaryType = "resources",
            attributes = FragmentAttributes {
                attribute(AndroidArtifacts.ARTIFACT_TYPE, AndroidArtifacts.ArtifactType.ANDROID_RES.type)
            }
        ) + IdeaKotlinPlatformDependencyResolver(
            binaryType = "keep-rules",
            attributes = FragmentAttributes {
                attribute(AndroidArtifacts.ARTIFACT_TYPE, AndroidArtifacts.ArtifactType.KEEP_RULES.type)
            }
        ) + IdeaKotlinPlatformDependencyResolver(
            binaryType = "android-symbol",
            attributes = FragmentAttributes {
                attribute(AndroidArtifacts.ARTIFACT_TYPE, AndroidArtifacts.ArtifactType.COMPILE_SYMBOL_LIST.type)
            }
        )).filterFragments { fragment -> androidDslKey in fragment.external }
    )

    ideaKotlinProjectModelBuilder.registerDependencyResolver(
        IdeaKotlinPlatformDependencyResolver(
            FragmentCompileDependenciesProvider,
            binaryType = IdeaKotlinFragmentBinaryDependency.CLASSPATH_BINARY_TYPE,
            attributes = FragmentAttributes {
                attribute(AndroidArtifacts.ARTIFACT_TYPE, AndroidArtifacts.ArtifactType.CLASSES_JAR.type)
            }
        ).filterFragments { fragment -> fragment !is KotlinGradleVariant && androidDslKey in fragment.external }
    )

    ideaKotlinProjectModelBuilder.registerDependencyResolver(
        IdeaKotlinDependencyResolver resolve@{
            project.getAndroidRuntimeJars().map { androidRuntimeJar ->
                IdeaKotlinFragmentResolvedBinaryDependencyImpl(
                    binaryType = IdeaKotlinFragmentBinaryDependency.CLASSPATH_BINARY_TYPE,
                    binaryFile = androidRuntimeJar,
                    coordinates = null
                )
            }.toSet()
        }.filterFragments { fragment -> fragment !is KotlinGradleVariant && androidDslKey in fragment.external }
    )

     */
}
