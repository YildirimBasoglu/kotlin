/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle.kpm.idea

import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.KotlinGradleFragment

object IdeaKotlinDependencyLogger : IdeaKotlinDependencyTransformer {
    override fun transform(
        fragment: KotlinGradleFragment, dependencies: Set<IdeaKotlinFragmentDependency>
    ): Set<IdeaKotlinFragmentDependency> {
        if (fragment.project.logger.isInfoEnabled) {
            val message = buildString {
                appendLine("Resolved dependencies for ${fragment.project.path}/${fragment.containingModule.name}/${fragment.name}")
                dependencies.forEach { dependency ->
                    appendLine(dependency.toString())
                }

                appendLine()
            }

            fragment.project.logger.info(message)
        }

        return dependencies
    }
}
