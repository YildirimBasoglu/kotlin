/*
 * Copyright 2010-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.resolve;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.descriptors.ScriptDescriptor;
import org.jetbrains.kotlin.psi.JetScript;
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowInfo;
import org.jetbrains.kotlin.resolve.lazy.ForceResolveUtil;
import org.jetbrains.kotlin.types.ErrorUtils;
import org.jetbrains.kotlin.types.JetType;
import org.jetbrains.kotlin.types.expressions.CoercionStrategy;
import org.jetbrains.kotlin.types.expressions.ExpressionTypingContext;
import org.jetbrains.kotlin.types.expressions.ExpressionTypingServices;

import java.util.Map;

import static org.jetbrains.kotlin.types.TypeUtils.NO_EXPECTED_TYPE;


// SCRIPT: resolve symbols in scripts
public class ScriptBodyResolver {

    @NotNull private final ExpressionTypingServices expressionTypingServices;
    @NotNull private final AdditionalCheckerProvider additionalCheckerProvider;

    public ScriptBodyResolver(
            @NotNull AdditionalCheckerProvider additionalCheckerProvider,
            @NotNull ExpressionTypingServices expressionTypingServices
    ) {
        this.additionalCheckerProvider = additionalCheckerProvider;
        this.expressionTypingServices = expressionTypingServices;
    }

    public void resolveScriptBodies(@NotNull BodiesResolveContext c) {
        for (Map.Entry<JetScript, ScriptDescriptor> e : c.getScripts().entrySet()) {
            ScriptDescriptor descriptor = e.getValue();
            ForceResolveUtil.forceResolveAllContents(descriptor);
        }
    }

    @NotNull
    public JetType resolveScriptReturnType(
            @NotNull JetScript script,
            @NotNull ScriptDescriptor scriptDescriptor,
            @NotNull BindingTrace trace
    ) {
        // Resolve all contents of the script
        ExpressionTypingContext context = ExpressionTypingContext.newContext(
                additionalCheckerProvider,
                trace,
                scriptDescriptor.getScopeForBodyResolution(),
                DataFlowInfo.EMPTY,
                NO_EXPECTED_TYPE
        );
        JetType returnType = expressionTypingServices.getBlockReturnedType(script.getBlockExpression(), CoercionStrategy.NO_COERCION, context).getType();
        if (returnType == null) {
            returnType = ErrorUtils.createErrorType("getBlockReturnedType returned null");
        }
        return returnType;
    }
}
