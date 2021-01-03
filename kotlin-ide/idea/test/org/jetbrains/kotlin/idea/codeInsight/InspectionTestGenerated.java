/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.codeInsight;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TestMetadata;
import org.jetbrains.kotlin.test.TestRoot;
import org.junit.runner.RunWith;

/*
 * This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}.
 * DO NOT MODIFY MANUALLY.
 */
@SuppressWarnings("all")
@TestRoot("idea")
@TestDataPath("$CONTENT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class InspectionTestGenerated extends AbstractInspectionTest {
    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/intentions")
    public static class Intentions extends AbstractInspectionTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("convertToStringTemplate/inspectionData/inspections.test")
        public void testConvertToStringTemplate_inspectionData_Inspections_test() throws Exception {
            runTest("testData/intentions/convertToStringTemplate/inspectionData/inspections.test");
        }

        @TestMetadata("destructuringInLambda/inspectionData/inspections.test")
        public void testDestructuringInLambda_inspectionData_Inspections_test() throws Exception {
            runTest("testData/intentions/destructuringInLambda/inspectionData/inspections.test");
        }

        @TestMetadata("iterationOverMap/inspectionData/inspections.test")
        public void testIterationOverMap_inspectionData_Inspections_test() throws Exception {
            runTest("testData/intentions/iterationOverMap/inspectionData/inspections.test");
        }

        @TestMetadata("objectLiteralToLambda/inspectionData/inspections.test")
        public void testObjectLiteralToLambda_inspectionData_Inspections_test() throws Exception {
            runTest("testData/intentions/objectLiteralToLambda/inspectionData/inspections.test");
        }

        @TestMetadata("removeExplicitSuperQualifier/inspectionData/inspections.test")
        public void testRemoveExplicitSuperQualifier_inspectionData_Inspections_test() throws Exception {
            runTest("testData/intentions/removeExplicitSuperQualifier/inspectionData/inspections.test");
        }

        @TestMetadata("removeExplicitTypeArguments/inspectionData/inspections.test")
        public void testRemoveExplicitTypeArguments_inspectionData_Inspections_test() throws Exception {
            runTest("testData/intentions/removeExplicitTypeArguments/inspectionData/inspections.test");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/inspections")
    public static class Inspections extends AbstractInspectionTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("addVarianceModifier/inspectionData/inspections.test")
        public void testAddVarianceModifier_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/addVarianceModifier/inspectionData/inspections.test");
        }

        @TestMetadata("allOpenSimple/inspectionData/inspections.test")
        public void testAllOpenSimple_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/allOpenSimple/inspectionData/inspections.test");
        }

        @TestMetadata("androidIllegalIdentifiers/inspectionData/inspections.test")
        public void testAndroidIllegalIdentifiers_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/androidIllegalIdentifiers/inspectionData/inspections.test");
        }

        @TestMetadata("arrayInDataClass/inspectionData/inspections.test")
        public void testArrayInDataClass_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/arrayInDataClass/inspectionData/inspections.test");
        }

        @TestMetadata("canBeParameter/inspectionData/inspections.test")
        public void testCanBeParameter_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/canBeParameter/inspectionData/inspections.test");
        }

        @TestMetadata("canBePrimaryConstructorProperty/inspectionData/inspections.test")
        public void testCanBePrimaryConstructorProperty_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/canBePrimaryConstructorProperty/inspectionData/inspections.test");
        }

        @TestMetadata("canBeVal/inspectionData/inspections.test")
        public void testCanBeVal_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/canBeVal/inspectionData/inspections.test");
        }

        @TestMetadata("conflictingExtensionProperty/inspectionData/inspections.test")
        public void testConflictingExtensionProperty_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/conflictingExtensionProperty/inspectionData/inspections.test");
        }

        @TestMetadata("constantConditionIf/inspectionData/inspections.test")
        public void testConstantConditionIf_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/constantConditionIf/inspectionData/inspections.test");
        }

        @TestMetadata("convertLambdaToReference/inspectionData/inspections.test")
        public void testConvertLambdaToReference_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/convertLambdaToReference/inspectionData/inspections.test");
        }

        @TestMetadata("convertSecondaryToPrimary/inspectionData/inspections.test")
        public void testConvertSecondaryToPrimary_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/convertSecondaryToPrimary/inspectionData/inspections.test");
        }

        @TestMetadata("copyWithoutNamedArguments/inspectionData/inspections.test")
        public void testCopyWithoutNamedArguments_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/copyWithoutNamedArguments/inspectionData/inspections.test");
        }

        @TestMetadata("coroutines/asyncResultUnused/inspectionData/inspections.test")
        public void testCoroutines_asyncResultUnused_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/coroutines/asyncResultUnused/inspectionData/inspections.test");
        }

        @TestMetadata("coroutines/directUseOfResultType/inspectionData/inspections.test")
        public void testCoroutines_directUseOfResultType_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/coroutines/directUseOfResultType/inspectionData/inspections.test");
        }

        @TestMetadata("dataClassPrivateConstructor/inspectionData/inspections.test")
        public void testDataClassPrivateConstructor_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/dataClassPrivateConstructor/inspectionData/inspections.test");
        }

        @TestMetadata("destructuringWrongName/inspectionData/inspections.test")
        public void testDestructuringWrongName_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/destructuringWrongName/inspectionData/inspections.test");
        }

        @TestMetadata("dynamic/inspectionData/inspections.test")
        public void testDynamic_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/dynamic/inspectionData/inspections.test");
        }

        @TestMetadata("emptyRange/inspectionData/inspections.test")
        public void testEmptyRange_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/emptyRange/inspectionData/inspections.test");
        }

        @TestMetadata("equalsAndHashCode/inspectionData/inspections.test")
        public void testEqualsAndHashCode_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/equalsAndHashCode/inspectionData/inspections.test");
        }

        @TestMetadata("forEachParameterNotUsed/inspectionData/inspections.test")
        public void testForEachParameterNotUsed_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/forEachParameterNotUsed/inspectionData/inspections.test");
        }

        @TestMetadata("gradleWrongPluginVersion/inspectionData/inspections.test")
        public void testGradleWrongPluginVersion_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/gradleWrongPluginVersion/inspectionData/inspections.test");
        }

        @TestMetadata("hasPlatformType/inspectionData/inspections.test")
        public void testHasPlatformType_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/hasPlatformType/inspectionData/inspections.test");
        }

        @TestMetadata("incompatibleAPI/inspectionData/inspections.test")
        public void testIncompatibleAPI_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/incompatibleAPI/inspectionData/inspections.test");
        }

        @TestMetadata("javaCollectionsStaticMethodOnImmutableList/inspectionData/inspections.test")
        public void testJavaCollectionsStaticMethodOnImmutableList_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/javaCollectionsStaticMethodOnImmutableList/inspectionData/inspections.test");
        }

        @TestMetadata("kt18195/inspectionData/inspections.test")
        public void testKt18195_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/kt18195/inspectionData/inspections.test");
        }

        @TestMetadata("leakingThis/inspectionData/inspections.test")
        public void testLeakingThis_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/leakingThis/inspectionData/inspections.test");
        }

        @TestMetadata("memberVisibilityCanBePrivate/inspectionData/inspections.test")
        public void testMemberVisibilityCanBePrivate_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/memberVisibilityCanBePrivate/inspectionData/inspections.test");
        }

        @TestMetadata("migrationFromClosedRange/inspectionData/inspections.test")
        public void testMigrationFromClosedRange_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/migrationFromClosedRange/inspectionData/inspections.test");
        }

        @TestMetadata("naming/class/inspectionData/inspections.test")
        public void testNaming_class_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/naming/class/inspectionData/inspections.test");
        }

        @TestMetadata("naming/enumEntry/inspectionData/inspections.test")
        public void testNaming_enumEntry_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/naming/enumEntry/inspectionData/inspections.test");
        }

        @TestMetadata("naming/function/inspectionData/inspections.test")
        public void testNaming_function_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/naming/function/inspectionData/inspections.test");
        }

        @TestMetadata("naming/objectProperty/inspectionData/inspections.test")
        public void testNaming_objectProperty_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/naming/objectProperty/inspectionData/inspections.test");
        }

        @TestMetadata("naming/package/inspectionData/inspections.test")
        public void testNaming_package_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/naming/package/inspectionData/inspections.test");
        }

        @TestMetadata("naming/privateProperty/inspectionData/inspections.test")
        public void testNaming_privateProperty_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/naming/privateProperty/inspectionData/inspections.test");
        }

        @TestMetadata("naming/property/inspectionData/inspections.test")
        public void testNaming_property_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/naming/property/inspectionData/inspections.test");
        }

        @TestMetadata("nullableBooleanElvis/inspectionData/inspections.test")
        public void testNullableBooleanElvis_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/nullableBooleanElvis/inspectionData/inspections.test");
        }

        @TestMetadata("overridingDeprecatedMember/inspectionData/inspections.test")
        public void testOverridingDeprecatedMember_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/overridingDeprecatedMember/inspectionData/inspections.test");
        }

        @TestMetadata("protectedInFinal/inspectionData/inspections.test")
        public void testProtectedInFinal_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/protectedInFinal/inspectionData/inspections.test");
        }

        @TestMetadata("publicApiImplicitType/inspectionData/inspections.test")
        public void testPublicApiImplicitType_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/publicApiImplicitType/inspectionData/inspections.test");
        }

        @TestMetadata("recursivePropertyAccessor/inspectionData/inspections.test")
        public void testRecursivePropertyAccessor_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/recursivePropertyAccessor/inspectionData/inspections.test");
        }

        @TestMetadata("redundantIf/inspectionData/inspections.test")
        public void testRedundantIf_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/redundantIf/inspectionData/inspections.test");
        }

        @TestMetadata("redundantModalityModifier/inspectionData/inspections.test")
        public void testRedundantModalityModifier_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/redundantModalityModifier/inspectionData/inspections.test");
        }

        @TestMetadata("redundantNotNullExtensionReceiverOfInline/inspectionData/inspections.test")
        public void testRedundantNotNullExtensionReceiverOfInline_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/redundantNotNullExtensionReceiverOfInline/inspectionData/inspections.test");
        }

        @TestMetadata("redundantSamConstructor/inspectionData/inspections.test")
        public void testRedundantSamConstructor_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/redundantSamConstructor/inspectionData/inspections.test");
        }

        @TestMetadata("redundantSemicolon/inspectionData/inspections.test")
        public void testRedundantSemicolon_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/redundantSemicolon/inspectionData/inspections.test");
        }

        @TestMetadata("redundantSuspendModifier/inspectionData/inspections.test")
        public void testRedundantSuspendModifier_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/redundantSuspendModifier/inspectionData/inspections.test");
        }

        @TestMetadata("redundantUnitReturnType/inspectionData/inspections.test")
        public void testRedundantUnitReturnType_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/redundantUnitReturnType/inspectionData/inspections.test");
        }

        @TestMetadata("redundantVisibilityModifierWithExplicitApi/inspectionData/inspections.test")
        public void testRedundantVisibilityModifierWithExplicitApi_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/redundantVisibilityModifierWithExplicitApi/inspectionData/inspections.test");
        }

        @TestMetadata("redundantVisibilityModifier/inspectionData/inspections.test")
        public void testRedundantVisibilityModifier_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/redundantVisibilityModifier/inspectionData/inspections.test");
        }

        @TestMetadata("redundantWith/inspectionData/inspections.test")
        public void testRedundantWith_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/redundantWith/inspectionData/inspections.test");
        }

        @TestMetadata("reformat/inspectionData/inspections.test")
        public void testReformat_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/reformat/inspectionData/inspections.test");
        }

        @TestMetadata("removeSetterParameterType/inspectionData/inspections.test")
        public void testRemoveSetterParameterType_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/removeSetterParameterType/inspectionData/inspections.test");
        }

        @TestMetadata("removeSingleExpressionStringTemplate/inspectionData/inspections.test")
        public void testRemoveSingleExpressionStringTemplate_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/removeSingleExpressionStringTemplate/inspectionData/inspections.test");
        }

        @TestMetadata("removeToStringInStringTemplate/inspectionData/inspections.test")
        public void testRemoveToStringInStringTemplate_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/removeToStringInStringTemplate/inspectionData/inspections.test");
        }

        @TestMetadata("replaceArrayEqualityOpWithArraysEquals/inspectionData/inspections.test")
        public void testReplaceArrayEqualityOpWithArraysEquals_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/replaceArrayEqualityOpWithArraysEquals/inspectionData/inspections.test");
        }

        @TestMetadata("replaceCallWithComparison/inspectionData/inspections.test")
        public void testReplaceCallWithComparison_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/replaceCallWithComparison/inspectionData/inspections.test");
        }

        @TestMetadata("replaceRangeToWithUntil/inspectionData/inspections.test")
        public void testReplaceRangeToWithUntil_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/replaceRangeToWithUntil/inspectionData/inspections.test");
        }

        @TestMetadata("sealedSubClassCanBeObject/inspectionData/inspections.test")
        public void testSealedSubClassCanBeObject_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/sealedSubClassCanBeObject/inspectionData/inspections.test");
        }

        @TestMetadata("spelling/inspectionData/inspections.test")
        public void testSpelling_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/spelling/inspectionData/inspections.test");
        }

        @TestMetadata("suspiciousEqualsCombination/inspectionData/inspections.test")
        public void testSuspiciousEqualsCombination_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/suspiciousEqualsCombination/inspectionData/inspections.test");
        }

        @TestMetadata("trailingCommaOff/inspectionData/inspections.test")
        public void testTrailingCommaOff_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/trailingCommaOff/inspectionData/inspections.test");
        }

        @TestMetadata("trailingCommaOn/inspectionData/inspections.test")
        public void testTrailingCommaOn_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/trailingCommaOn/inspectionData/inspections.test");
        }

        @TestMetadata("twoSetOfTypeparameters/inspectionData/inspections.test")
        public void testTwoSetOfTypeparameters_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/twoSetOfTypeparameters/inspectionData/inspections.test");
        }

        @TestMetadata("unusedEquals/inspectionData/inspections.test")
        public void testUnusedEquals_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/unusedEquals/inspectionData/inspections.test");
        }

        @TestMetadata("unusedImport/inspectionData/inspections.test")
        public void testUnusedImport_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/unusedImport/inspectionData/inspections.test");
        }

        @TestMetadata("unusedLambdaExpressionBody/inspectionData/inspections.test")
        public void testUnusedLambdaExpressionBody_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/unusedLambdaExpressionBody/inspectionData/inspections.test");
        }

        @TestMetadata("unusedReceiverParameter/inspectionData/inspections.test")
        public void testUnusedReceiverParameter_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/unusedReceiverParameter/inspectionData/inspections.test");
        }

        @TestMetadata("unusedSymbol/class/inspectionData/inspections.test")
        public void testUnusedSymbol_class_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/unusedSymbol/class/inspectionData/inspections.test");
        }

        @TestMetadata("unusedSymbol/enum/inspectionData/inspections.test")
        public void testUnusedSymbol_enum_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/unusedSymbol/enum/inspectionData/inspections.test");
        }

        @TestMetadata("unusedSymbol/functionMain/inspectionData/inspections.test")
        public void testUnusedSymbol_functionMain_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/unusedSymbol/functionMain/inspectionData/inspections.test");
        }

        @TestMetadata("unusedSymbol/function/inspectionData/inspections.test")
        public void testUnusedSymbol_function_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/unusedSymbol/function/inspectionData/inspections.test");
        }

        @TestMetadata("unusedSymbol/js/inspectionData/inspections.test")
        public void testUnusedSymbol_js_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/unusedSymbol/js/inspectionData/inspections.test");
        }

        @TestMetadata("unusedSymbol/object/inspectionData/inspections.test")
        public void testUnusedSymbol_object_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/unusedSymbol/object/inspectionData/inspections.test");
        }

        @TestMetadata("unusedSymbol/parameter/inspectionData/inspections.test")
        public void testUnusedSymbol_parameter_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/unusedSymbol/parameter/inspectionData/inspections.test");
        }

        @TestMetadata("unusedSymbol/property/inspectionData/inspections.test")
        public void testUnusedSymbol_property_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/unusedSymbol/property/inspectionData/inspections.test");
        }

        @TestMetadata("unusedSymbol/typeAlias/inspectionData/inspections.test")
        public void testUnusedSymbol_typeAlias_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/unusedSymbol/typeAlias/inspectionData/inspections.test");
        }

        @TestMetadata("unusedSymbol/typeParameter/inspectionData/inspections.test")
        public void testUnusedSymbol_typeParameter_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/unusedSymbol/typeParameter/inspectionData/inspections.test");
        }

        @TestMetadata("wrapUnaryOperator/inspectionData/inspections.test")
        public void testWrapUnaryOperator_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspections/wrapUnaryOperator/inspectionData/inspections.test");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/inspectionsLocal")
    public static class InspectionsLocal extends AbstractInspectionTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("branched/ifThenToElvis/inspectionData/inspections.test")
        public void testBranched_ifThenToElvis_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspectionsLocal/branched/ifThenToElvis/inspectionData/inspections.test");
        }

        @TestMetadata("branched/ifThenToSafeAccess/inspectionData/inspections.test")
        public void testBranched_ifThenToSafeAccess_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspectionsLocal/branched/ifThenToSafeAccess/inspectionData/inspections.test");
        }

        @TestMetadata("conventionNameCalls/replaceGetOrSet/inspectionData/inspections.test")
        public void testConventionNameCalls_replaceGetOrSet_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspectionsLocal/conventionNameCalls/replaceGetOrSet/inspectionData/inspections.test");
        }

        @TestMetadata("deprecatedCallableAddReplaceWith/inspectionData/inspections.test")
        public void testDeprecatedCallableAddReplaceWith_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspectionsLocal/deprecatedCallableAddReplaceWith/inspectionData/inspections.test");
        }

        @TestMetadata("simplifyNegatedBinaryExpression/inspectionData/inspections.test")
        public void testSimplifyNegatedBinaryExpression_inspectionData_Inspections_test() throws Exception {
            runTest("testData/inspectionsLocal/simplifyNegatedBinaryExpression/inspectionData/inspections.test");
        }
    }
}
