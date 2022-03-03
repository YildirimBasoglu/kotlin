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
import org.jetbrains.kotlin.gradle.kpm.external.ExternalVariantApi
import org.jetbrains.kotlin.gradle.kpm.external.external
import org.jetbrains.kotlin.gradle.kpm.external.ideaKotlinProjectModelBuilder
import org.jetbrains.kotlin.gradle.kpm.external.project
import org.jetbrains.kotlin.gradle.kpm.idea.*
import org.jetbrains.kotlin.gradle.kpm.idea.IdeaKotlinDependency.Companion.CLASSPATH_BINARY_TYPE
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType
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
                    attribute(KotlinPlatformType.attribute, KotlinPlatformType.androidJvm)
                    namedAttribute(BuildTypeAttr.ATTRIBUTE, "release")
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
