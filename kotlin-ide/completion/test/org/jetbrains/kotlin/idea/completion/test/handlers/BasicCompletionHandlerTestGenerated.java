/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.completion.test.handlers;

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
@TestRoot("completion")
@TestDataPath("$CONTENT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
@TestMetadata("testData/handlers/basic")
public class BasicCompletionHandlerTestGenerated extends AbstractBasicCompletionHandlerTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    @TestMetadata("AddLabelToReturn.kt")
    public void testAddLabelToReturn() throws Exception {
        runTest("testData/handlers/basic/AddLabelToReturn.kt");
    }

    @TestMetadata("ClassKeywordBeforeName.kt")
    public void testClassKeywordBeforeName() throws Exception {
        runTest("testData/handlers/basic/ClassKeywordBeforeName.kt");
    }

    @TestMetadata("ClassNameForMethodWithPackageConflict.kt")
    public void testClassNameForMethodWithPackageConflict() throws Exception {
        runTest("testData/handlers/basic/ClassNameForMethodWithPackageConflict.kt");
    }

    @TestMetadata("ClassNameForMethodWithPackageConflict2.kt")
    public void testClassNameForMethodWithPackageConflict2() throws Exception {
        runTest("testData/handlers/basic/ClassNameForMethodWithPackageConflict2.kt");
    }

    @TestMetadata("ClassNameWithPackageConflict.kt")
    public void testClassNameWithPackageConflict() throws Exception {
        runTest("testData/handlers/basic/ClassNameWithPackageConflict.kt");
    }

    @TestMetadata("ClassWithClassObject.kt")
    public void testClassWithClassObject() throws Exception {
        runTest("testData/handlers/basic/ClassWithClassObject.kt");
    }

    @TestMetadata("DoNotUseParenthesisOnNextLine.kt")
    public void testDoNotUseParenthesisOnNextLine() throws Exception {
        runTest("testData/handlers/basic/DoNotUseParenthesisOnNextLine.kt");
    }

    @TestMetadata("EA70229.kt")
    public void testEA70229() throws Exception {
        runTest("testData/handlers/basic/EA70229.kt");
    }

    @TestMetadata("ExtensionFunctionTypeVariable1.kt")
    public void testExtensionFunctionTypeVariable1() throws Exception {
        runTest("testData/handlers/basic/ExtensionFunctionTypeVariable1.kt");
    }

    @TestMetadata("ExtensionFunctionTypeVariable2.kt")
    public void testExtensionFunctionTypeVariable2() throws Exception {
        runTest("testData/handlers/basic/ExtensionFunctionTypeVariable2.kt");
    }

    @TestMetadata("ExtensionReceiverTypeArg.kt")
    public void testExtensionReceiverTypeArg() throws Exception {
        runTest("testData/handlers/basic/ExtensionReceiverTypeArg.kt");
    }

    @TestMetadata("FirstTypeArgument.kt")
    public void testFirstTypeArgument() throws Exception {
        runTest("testData/handlers/basic/FirstTypeArgument.kt");
    }

    @TestMetadata("GenericFunctionWithTab.kt")
    public void testGenericFunctionWithTab() throws Exception {
        runTest("testData/handlers/basic/GenericFunctionWithTab.kt");
    }

    @TestMetadata("GenericFunctionWithTab2.kt")
    public void testGenericFunctionWithTab2() throws Exception {
        runTest("testData/handlers/basic/GenericFunctionWithTab2.kt");
    }

    @TestMetadata("GetOperator.kt")
    public void testGetOperator() throws Exception {
        runTest("testData/handlers/basic/GetOperator.kt");
    }

    @TestMetadata("InterfaceNameBeforeRunBug.kt")
    public void testInterfaceNameBeforeRunBug() throws Exception {
        runTest("testData/handlers/basic/InterfaceNameBeforeRunBug.kt");
    }

    @TestMetadata("JavaSAM.kt")
    public void testJavaSAM() throws Exception {
        runTest("testData/handlers/basic/JavaSAM.kt");
    }

    @TestMetadata("KT11633.kt")
    public void testKT11633() throws Exception {
        runTest("testData/handlers/basic/KT11633.kt");
    }

    @TestMetadata("KT12328.kt")
    public void testKT12328() throws Exception {
        runTest("testData/handlers/basic/KT12328.kt");
    }

    @TestMetadata("KT14130.kt")
    public void testKT14130() throws Exception {
        runTest("testData/handlers/basic/KT14130.kt");
    }

    @TestMetadata("KT19863.kt")
    public void testKT19863() throws Exception {
        runTest("testData/handlers/basic/KT19863.kt");
    }

    @TestMetadata("KT19864.kt")
    public void testKT19864() throws Exception {
        runTest("testData/handlers/basic/KT19864.kt");
    }

    @TestMetadata("KT23627.kt")
    public void testKT23627() throws Exception {
        runTest("testData/handlers/basic/KT23627.kt");
    }

    @TestMetadata("KT36306.kt")
    public void testKT36306() throws Exception {
        runTest("testData/handlers/basic/KT36306.kt");
    }

    @TestMetadata("NestedTypeArg.kt")
    public void testNestedTypeArg() throws Exception {
        runTest("testData/handlers/basic/NestedTypeArg.kt");
    }

    @TestMetadata("NoTailFromSmart.kt")
    public void testNoTailFromSmart() throws Exception {
        runTest("testData/handlers/basic/NoTailFromSmart.kt");
    }

    @TestMetadata("PreferClassToConstructor.kt")
    public void testPreferClassToConstructor() throws Exception {
        runTest("testData/handlers/basic/PreferClassToConstructor.kt");
    }

    @TestMetadata("PreferMatchingKeyword.kt")
    public void testPreferMatchingKeyword() throws Exception {
        runTest("testData/handlers/basic/PreferMatchingKeyword.kt");
    }

    @TestMetadata("ReceiverParam.kt")
    public void testReceiverParam() throws Exception {
        runTest("testData/handlers/basic/ReceiverParam.kt");
    }

    @TestMetadata("ReceiverParam2.kt")
    public void testReceiverParam2() throws Exception {
        runTest("testData/handlers/basic/ReceiverParam2.kt");
    }

    @TestMetadata("ReplaceFunctionCallByProperty.kt")
    public void testReplaceFunctionCallByProperty() throws Exception {
        runTest("testData/handlers/basic/ReplaceFunctionCallByProperty.kt");
    }

    @TestMetadata("ReplaceFunctionCallByPropertyArgs.kt")
    public void testReplaceFunctionCallByPropertyArgs() throws Exception {
        runTest("testData/handlers/basic/ReplaceFunctionCallByPropertyArgs.kt");
    }

    @TestMetadata("SecondTypeArg.kt")
    public void testSecondTypeArg() throws Exception {
        runTest("testData/handlers/basic/SecondTypeArg.kt");
    }

    @TestMetadata("SpaceAfterParenthesisBug.kt")
    public void testSpaceAfterParenthesisBug() throws Exception {
        runTest("testData/handlers/basic/SpaceAfterParenthesisBug.kt");
    }

    @TestMetadata("StaticFunctionFromJavaWithConflict.kt")
    public void testStaticFunctionFromJavaWithConflict() throws Exception {
        runTest("testData/handlers/basic/StaticFunctionFromJavaWithConflict.kt");
    }

    @TestMetadata("StringFakeConstructor.kt")
    public void testStringFakeConstructor() throws Exception {
        runTest("testData/handlers/basic/StringFakeConstructor.kt");
    }

    @TestMetadata("SuperMethod.kt")
    public void testSuperMethod() throws Exception {
        runTest("testData/handlers/basic/SuperMethod.kt");
    }

    @TestMetadata("SuperMethod2.kt")
    public void testSuperMethod2() throws Exception {
        runTest("testData/handlers/basic/SuperMethod2.kt");
    }

    @TestMetadata("SuperTypeArg.kt")
    public void testSuperTypeArg() throws Exception {
        runTest("testData/handlers/basic/SuperTypeArg.kt");
    }

    @TestMetadata("SyntheticExtension.kt")
    public void testSyntheticExtension() throws Exception {
        runTest("testData/handlers/basic/SyntheticExtension.kt");
    }

    @TestMetadata("TypeInferedFromWrapperType.kt")
    public void testTypeInferedFromWrapperType() throws Exception {
        runTest("testData/handlers/basic/TypeInferedFromWrapperType.kt");
    }

    @TestMetadata("TypeParameter.kt")
    public void testTypeParameter() throws Exception {
        runTest("testData/handlers/basic/TypeParameter.kt");
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/handlers/basic/annotation")
    public static class Annotation extends AbstractBasicCompletionHandlerTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("AnnotationInBrackets.kt")
        public void testAnnotationInBrackets() throws Exception {
            runTest("testData/handlers/basic/annotation/AnnotationInBrackets.kt");
        }

        @TestMetadata("AnnotationInClassAddImport.kt")
        public void testAnnotationInClassAddImport() throws Exception {
            runTest("testData/handlers/basic/annotation/AnnotationInClassAddImport.kt");
        }

        @TestMetadata("AnnotationInCompanionObjectAddImport.kt")
        public void testAnnotationInCompanionObjectAddImport() throws Exception {
            runTest("testData/handlers/basic/annotation/AnnotationInCompanionObjectAddImport.kt");
        }

        @TestMetadata("KT12077.kt")
        public void testKT12077() throws Exception {
            runTest("testData/handlers/basic/annotation/KT12077.kt");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/handlers/basic/callableReference")
    public static class CallableReference extends AbstractBasicCompletionHandlerTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("ClassConstructor.kt")
        public void testClassConstructor() throws Exception {
            runTest("testData/handlers/basic/callableReference/ClassConstructor.kt");
        }

        @TestMetadata("EmptyQualifier.kt")
        public void testEmptyQualifier() throws Exception {
            runTest("testData/handlers/basic/callableReference/EmptyQualifier.kt");
        }

        @TestMetadata("NonEmptyQualifier.kt")
        public void testNonEmptyQualifier() throws Exception {
            runTest("testData/handlers/basic/callableReference/NonEmptyQualifier.kt");
        }

        @TestMetadata("NotImportedTopLevel.kt")
        public void testNotImportedTopLevel() throws Exception {
            runTest("testData/handlers/basic/callableReference/NotImportedTopLevel.kt");
        }

        @TestMetadata("Property.kt")
        public void testProperty() throws Exception {
            runTest("testData/handlers/basic/callableReference/Property.kt");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/handlers/basic/exclChar")
    public static class ExclChar extends AbstractBasicCompletionHandlerTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("1.kt")
        public void test1() throws Exception {
            runTest("testData/handlers/basic/exclChar/1.kt");
        }

        @TestMetadata("2.kt")
        public void test2() throws Exception {
            runTest("testData/handlers/basic/exclChar/2.kt");
        }

        @TestMetadata("3.kt")
        public void test3() throws Exception {
            runTest("testData/handlers/basic/exclChar/3.kt");
        }

        @TestMetadata("4.kt")
        public void test4() throws Exception {
            runTest("testData/handlers/basic/exclChar/4.kt");
        }

        @TestMetadata("5.kt")
        public void test5() throws Exception {
            runTest("testData/handlers/basic/exclChar/5.kt");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/handlers/basic/extensionMethodInObject")
    public static class ExtensionMethodInObject extends AbstractBasicCompletionHandlerTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("CompanionObjectInSameFileExplicitReceiver.kt")
        public void testCompanionObjectInSameFileExplicitReceiver() throws Exception {
            runTest("testData/handlers/basic/extensionMethodInObject/CompanionObjectInSameFileExplicitReceiver.kt");
        }

        @TestMetadata("CompanionObjectInSameFileImplicitReceiver.kt")
        public void testCompanionObjectInSameFileImplicitReceiver() throws Exception {
            runTest("testData/handlers/basic/extensionMethodInObject/CompanionObjectInSameFileImplicitReceiver.kt");
        }

        @TestMetadata("NestedCompanionObjectInSameFileExplicitReceiver.kt")
        public void testNestedCompanionObjectInSameFileExplicitReceiver() throws Exception {
            runTest("testData/handlers/basic/extensionMethodInObject/NestedCompanionObjectInSameFileExplicitReceiver.kt");
        }

        @TestMetadata("NestedCompanionObjectInSameFileImplicitReceiver.kt")
        public void testNestedCompanionObjectInSameFileImplicitReceiver() throws Exception {
            runTest("testData/handlers/basic/extensionMethodInObject/NestedCompanionObjectInSameFileImplicitReceiver.kt");
        }

        @TestMetadata("NestedObjectInSameFileExplicitReceiver.kt")
        public void testNestedObjectInSameFileExplicitReceiver() throws Exception {
            runTest("testData/handlers/basic/extensionMethodInObject/NestedObjectInSameFileExplicitReceiver.kt");
        }

        @TestMetadata("NestedObjectInSameFileImplicitReceiver.kt")
        public void testNestedObjectInSameFileImplicitReceiver() throws Exception {
            runTest("testData/handlers/basic/extensionMethodInObject/NestedObjectInSameFileImplicitReceiver.kt");
        }

        @TestMetadata("ObjectInSameFileExplicitReceiver.kt")
        public void testObjectInSameFileExplicitReceiver() throws Exception {
            runTest("testData/handlers/basic/extensionMethodInObject/ObjectInSameFileExplicitReceiver.kt");
        }

        @TestMetadata("ObjectInSameFileImplicitReceiver.kt")
        public void testObjectInSameFileImplicitReceiver() throws Exception {
            runTest("testData/handlers/basic/extensionMethodInObject/ObjectInSameFileImplicitReceiver.kt");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/handlers/basic/highOrderFunctions")
    public static class HighOrderFunctions extends AbstractBasicCompletionHandlerTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("ContextVariable.kt")
        public void testContextVariable() throws Exception {
            runTest("testData/handlers/basic/highOrderFunctions/ContextVariable.kt");
        }

        @TestMetadata("ContextVariableDot.kt")
        public void testContextVariableDot() throws Exception {
            runTest("testData/handlers/basic/highOrderFunctions/ContextVariableDot.kt");
        }

        @TestMetadata("ContextVariableTypeArgsNeeded.kt")
        public void testContextVariableTypeArgsNeeded() throws Exception {
            runTest("testData/handlers/basic/highOrderFunctions/ContextVariableTypeArgsNeeded.kt");
        }

        @TestMetadata("ForceParenthesisForTabChar.kt")
        public void testForceParenthesisForTabChar() throws Exception {
            runTest("testData/handlers/basic/highOrderFunctions/ForceParenthesisForTabChar.kt");
        }

        @TestMetadata("FunctionLiteralInsertOnSpace.kt")
        public void testFunctionLiteralInsertOnSpace() throws Exception {
            runTest("testData/handlers/basic/highOrderFunctions/FunctionLiteralInsertOnSpace.kt");
        }

        @TestMetadata("FunctionLiteralInsertWhenNoSpacesForBraces.kt")
        public void testFunctionLiteralInsertWhenNoSpacesForBraces() throws Exception {
            runTest("testData/handlers/basic/highOrderFunctions/FunctionLiteralInsertWhenNoSpacesForBraces.kt");
        }

        @TestMetadata("HigherOrderFunction.kt")
        public void testHigherOrderFunction() throws Exception {
            runTest("testData/handlers/basic/highOrderFunctions/HigherOrderFunction.kt");
        }

        @TestMetadata("HigherOrderFunctionWithArg.kt")
        public void testHigherOrderFunctionWithArg() throws Exception {
            runTest("testData/handlers/basic/highOrderFunctions/HigherOrderFunctionWithArg.kt");
        }

        @TestMetadata("HigherOrderFunctionWithArgs1.kt")
        public void testHigherOrderFunctionWithArgs1() throws Exception {
            runTest("testData/handlers/basic/highOrderFunctions/HigherOrderFunctionWithArgs1.kt");
        }

        @TestMetadata("HigherOrderFunctionWithArgs2.kt")
        public void testHigherOrderFunctionWithArgs2() throws Exception {
            runTest("testData/handlers/basic/highOrderFunctions/HigherOrderFunctionWithArgs2.kt");
        }

        @TestMetadata("HigherOrderFunctionWithArgs3.kt")
        public void testHigherOrderFunctionWithArgs3() throws Exception {
            runTest("testData/handlers/basic/highOrderFunctions/HigherOrderFunctionWithArgs3.kt");
        }

        @TestMetadata("HigherOrderSuspendFunctionWithArgs.kt")
        public void testHigherOrderSuspendFunctionWithArgs() throws Exception {
            runTest("testData/handlers/basic/highOrderFunctions/HigherOrderSuspendFunctionWithArgs.kt");
        }

        @TestMetadata("InsertFunctionWithSingleParameterWithBrace.kt")
        public void testInsertFunctionWithSingleParameterWithBrace() throws Exception {
            runTest("testData/handlers/basic/highOrderFunctions/InsertFunctionWithSingleParameterWithBrace.kt");
        }

        @TestMetadata("OptionalParameters1.kt")
        public void testOptionalParameters1() throws Exception {
            runTest("testData/handlers/basic/highOrderFunctions/OptionalParameters1.kt");
        }

        @TestMetadata("OptionalParameters2.kt")
        public void testOptionalParameters2() throws Exception {
            runTest("testData/handlers/basic/highOrderFunctions/OptionalParameters2.kt");
        }

        @TestMetadata("OptionalParameters3.kt")
        public void testOptionalParameters3() throws Exception {
            runTest("testData/handlers/basic/highOrderFunctions/OptionalParameters3.kt");
        }

        @TestMetadata("ParameterTypeIsDerivedFromFunction.kt")
        public void testParameterTypeIsDerivedFromFunction() throws Exception {
            runTest("testData/handlers/basic/highOrderFunctions/ParameterTypeIsDerivedFromFunction.kt");
        }

        @TestMetadata("ReplaceByLambdaTemplateNoClosingParenth.kt")
        public void testReplaceByLambdaTemplateNoClosingParenth() throws Exception {
            runTest("testData/handlers/basic/highOrderFunctions/ReplaceByLambdaTemplateNoClosingParenth.kt");
        }

        @TestMetadata("SameTypeParameters.kt")
        public void testSameTypeParameters() throws Exception {
            runTest("testData/handlers/basic/highOrderFunctions/SameTypeParameters.kt");
        }

        @TestMetadata("SameTypeParameters2.kt")
        public void testSameTypeParameters2() throws Exception {
            runTest("testData/handlers/basic/highOrderFunctions/SameTypeParameters2.kt");
        }

        @TestMetadata("SameTypeParameters3.kt")
        public void testSameTypeParameters3() throws Exception {
            runTest("testData/handlers/basic/highOrderFunctions/SameTypeParameters3.kt");
        }

        @TestMetadata("WithArgsEmptyLambdaAfter.kt")
        public void testWithArgsEmptyLambdaAfter() throws Exception {
            runTest("testData/handlers/basic/highOrderFunctions/WithArgsEmptyLambdaAfter.kt");
        }

        @TestMetadata("WithArgsNonEmptyLambdaAfter.kt")
        public void testWithArgsNonEmptyLambdaAfter() throws Exception {
            runTest("testData/handlers/basic/highOrderFunctions/WithArgsNonEmptyLambdaAfter.kt");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/handlers/basic/importAliases")
    public static class ImportAliases extends AbstractBasicCompletionHandlerTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("CompanionObject.kt")
        public void testCompanionObject() throws Exception {
            runTest("testData/handlers/basic/importAliases/CompanionObject.kt");
        }

        @TestMetadata("ExtensionFun.kt")
        public void testExtensionFun() throws Exception {
            runTest("testData/handlers/basic/importAliases/ExtensionFun.kt");
        }

        @TestMetadata("ExtensionVal.kt")
        public void testExtensionVal() throws Exception {
            runTest("testData/handlers/basic/importAliases/ExtensionVal.kt");
        }

        @TestMetadata("KDoc.kt")
        public void testKDoc() throws Exception {
            runTest("testData/handlers/basic/importAliases/KDoc.kt");
        }

        @TestMetadata("TopLevelFun.kt")
        public void testTopLevelFun() throws Exception {
            runTest("testData/handlers/basic/importAliases/TopLevelFun.kt");
        }

        @TestMetadata("TopLevelVal.kt")
        public void testTopLevelVal() throws Exception {
            runTest("testData/handlers/basic/importAliases/TopLevelVal.kt");
        }

        @TestMetadata("Type.kt")
        public void testType() throws Exception {
            runTest("testData/handlers/basic/importAliases/Type.kt");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/handlers/basic/override")
    public static class Override extends AbstractBasicCompletionHandlerTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("AfterFunKeyword.kt")
        public void testAfterFunKeyword() throws Exception {
            runTest("testData/handlers/basic/override/AfterFunKeyword.kt");
        }

        @TestMetadata("AfterFunKeywordKeepModifiersBefore.kt")
        public void testAfterFunKeywordKeepModifiersBefore() throws Exception {
            runTest("testData/handlers/basic/override/AfterFunKeywordKeepModifiersBefore.kt");
        }

        @TestMetadata("AfterValKeyword.kt")
        public void testAfterValKeyword() throws Exception {
            runTest("testData/handlers/basic/override/AfterValKeyword.kt");
        }

        @TestMetadata("AfterValKeywordInConstructorParameter.kt")
        public void testAfterValKeywordInConstructorParameter() throws Exception {
            runTest("testData/handlers/basic/override/AfterValKeywordInConstructorParameter.kt");
        }

        @TestMetadata("ExpectClassValOverride.kt")
        public void testExpectClassValOverride() throws Exception {
            runTest("testData/handlers/basic/override/ExpectClassValOverride.kt");
        }

        @TestMetadata("ImplementFunction.kt")
        public void testImplementFunction() throws Exception {
            runTest("testData/handlers/basic/override/ImplementFunction.kt");
        }

        @TestMetadata("ImplementVal.kt")
        public void testImplementVal() throws Exception {
            runTest("testData/handlers/basic/override/ImplementVal.kt");
        }

        @TestMetadata("ImplementVar.kt")
        public void testImplementVar() throws Exception {
            runTest("testData/handlers/basic/override/ImplementVar.kt");
        }

        @TestMetadata("KeepAnnotationBefore.kt")
        public void testKeepAnnotationBefore() throws Exception {
            runTest("testData/handlers/basic/override/KeepAnnotationBefore.kt");
        }

        @TestMetadata("KeepModifiersBefore.kt")
        public void testKeepModifiersBefore() throws Exception {
            runTest("testData/handlers/basic/override/KeepModifiersBefore.kt");
        }

        @TestMetadata("kt25312.kt")
        public void testKt25312() throws Exception {
            runTest("testData/handlers/basic/override/kt25312.kt");
        }

        @TestMetadata("OverrideFunction.kt")
        public void testOverrideFunction() throws Exception {
            runTest("testData/handlers/basic/override/OverrideFunction.kt");
        }

        @TestMetadata("OverrideVar.kt")
        public void testOverrideVar() throws Exception {
            runTest("testData/handlers/basic/override/OverrideVar.kt");
        }

        @TestMetadata("PublicValInConstructorParameter.kt")
        public void testPublicValInConstructorParameter() throws Exception {
            runTest("testData/handlers/basic/override/PublicValInConstructorParameter.kt");
        }

        @TestMetadata("Suspend.kt")
        public void testSuspend() throws Exception {
            runTest("testData/handlers/basic/override/Suspend.kt");
        }

        @TestMetadata("TypeFunctionName.kt")
        public void testTypeFunctionName() throws Exception {
            runTest("testData/handlers/basic/override/TypeFunctionName.kt");
        }

        @TestMetadata("TypeNameInConstructorParameter.kt")
        public void testTypeNameInConstructorParameter() throws Exception {
            runTest("testData/handlers/basic/override/TypeNameInConstructorParameter.kt");
        }

        @TestMetadata("ValInConstructorParameter.kt")
        public void testValInConstructorParameter() throws Exception {
            runTest("testData/handlers/basic/override/ValInConstructorParameter.kt");
        }

        @TestMetadata("ValInConstructorParameter2.kt")
        public void testValInConstructorParameter2() throws Exception {
            runTest("testData/handlers/basic/override/ValInConstructorParameter2.kt");
        }

        @TestMetadata("ValInConstructorParameter3.kt")
        public void testValInConstructorParameter3() throws Exception {
            runTest("testData/handlers/basic/override/ValInConstructorParameter3.kt");
        }

        @TestMetadata("ValInConstructorParameter4.kt")
        public void testValInConstructorParameter4() throws Exception {
            runTest("testData/handlers/basic/override/ValInConstructorParameter4.kt");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/handlers/basic/parameterNameAndType")
    public static class ParameterNameAndType extends AbstractBasicCompletionHandlerTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("CodeStyleSettings.kt")
        public void testCodeStyleSettings() throws Exception {
            runTest("testData/handlers/basic/parameterNameAndType/CodeStyleSettings.kt");
        }

        @TestMetadata("Comma.kt")
        public void testComma() throws Exception {
            runTest("testData/handlers/basic/parameterNameAndType/Comma.kt");
        }

        @TestMetadata("InsertImport.kt")
        public void testInsertImport() throws Exception {
            runTest("testData/handlers/basic/parameterNameAndType/InsertImport.kt");
        }

        @TestMetadata("NoInsertionOnTypingColon.kt")
        public void testNoInsertionOnTypingColon() throws Exception {
            runTest("testData/handlers/basic/parameterNameAndType/NoInsertionOnTypingColon.kt");
        }

        @TestMetadata("NoInsertionOnTypingSpace.kt")
        public void testNoInsertionOnTypingSpace() throws Exception {
            runTest("testData/handlers/basic/parameterNameAndType/NoInsertionOnTypingSpace.kt");
        }

        @TestMetadata("ParameterInFile.kt")
        public void testParameterInFile() throws Exception {
            runTest("testData/handlers/basic/parameterNameAndType/ParameterInFile.kt");
        }

        @TestMetadata("ParameterInFile2.kt")
        public void testParameterInFile2() throws Exception {
            runTest("testData/handlers/basic/parameterNameAndType/ParameterInFile2.kt");
        }

        @TestMetadata("Simple.kt")
        public void testSimple() throws Exception {
            runTest("testData/handlers/basic/parameterNameAndType/Simple.kt");
        }

        @TestMetadata("TabReplace1.kt")
        public void testTabReplace1() throws Exception {
            runTest("testData/handlers/basic/parameterNameAndType/TabReplace1.kt");
        }

        @TestMetadata("TabReplace2.kt")
        public void testTabReplace2() throws Exception {
            runTest("testData/handlers/basic/parameterNameAndType/TabReplace2.kt");
        }

        @TestMetadata("TabReplace3.kt")
        public void testTabReplace3() throws Exception {
            runTest("testData/handlers/basic/parameterNameAndType/TabReplace3.kt");
        }

        @TestMetadata("TypeParameter.kt")
        public void testTypeParameter() throws Exception {
            runTest("testData/handlers/basic/parameterNameAndType/TypeParameter.kt");
        }

        @TestMetadata("UserPrefix.kt")
        public void testUserPrefix() throws Exception {
            runTest("testData/handlers/basic/parameterNameAndType/UserPrefix.kt");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/handlers/basic/staticMemberOfNotImported")
    public static class StaticMemberOfNotImported extends AbstractBasicCompletionHandlerTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("AmbigiousExtension.kt")
        public void testAmbigiousExtension() throws Exception {
            runTest("testData/handlers/basic/staticMemberOfNotImported/AmbigiousExtension.kt");
        }

        @TestMetadata("AmbigiousName.kt")
        public void testAmbigiousName() throws Exception {
            runTest("testData/handlers/basic/staticMemberOfNotImported/AmbigiousName.kt");
        }

        @TestMetadata("CompanionObjectMember.kt")
        public void testCompanionObjectMember() throws Exception {
            runTest("testData/handlers/basic/staticMemberOfNotImported/CompanionObjectMember.kt");
        }

        @TestMetadata("EnumEntry.kt")
        public void testEnumEntry() throws Exception {
            runTest("testData/handlers/basic/staticMemberOfNotImported/EnumEntry.kt");
        }

        @TestMetadata("ObjectMember.kt")
        public void testObjectMember() throws Exception {
            runTest("testData/handlers/basic/staticMemberOfNotImported/ObjectMember.kt");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/handlers/basic/staticMembers")
    public static class StaticMembers extends AbstractBasicCompletionHandlerTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("classObjectMethod.kt")
        public void testClassObjectMethod() throws Exception {
            runTest("testData/handlers/basic/staticMembers/classObjectMethod.kt");
        }

        @TestMetadata("ImportFromCompanionObject.kt")
        public void testImportFromCompanionObject() throws Exception {
            runTest("testData/handlers/basic/staticMembers/ImportFromCompanionObject.kt");
        }

        @TestMetadata("ImportJavaStaticMethod.kt")
        public void testImportJavaStaticMethod() throws Exception {
            runTest("testData/handlers/basic/staticMembers/ImportJavaStaticMethod.kt");
        }

        @TestMetadata("JavaStaticMethod.kt")
        public void testJavaStaticMethod() throws Exception {
            runTest("testData/handlers/basic/staticMembers/JavaStaticMethod.kt");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/handlers/basic/stringTemplate")
    public static class StringTemplate extends AbstractBasicCompletionHandlerTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("1.kt")
        public void test1() throws Exception {
            runTest("testData/handlers/basic/stringTemplate/1.kt");
        }

        @TestMetadata("2.kt")
        public void test2() throws Exception {
            runTest("testData/handlers/basic/stringTemplate/2.kt");
        }

        @TestMetadata("3.kt")
        public void test3() throws Exception {
            runTest("testData/handlers/basic/stringTemplate/3.kt");
        }

        @TestMetadata("4.kt")
        public void test4() throws Exception {
            runTest("testData/handlers/basic/stringTemplate/4.kt");
        }

        @TestMetadata("AfterDot1.kt")
        public void testAfterDot1() throws Exception {
            runTest("testData/handlers/basic/stringTemplate/AfterDot1.kt");
        }

        @TestMetadata("AfterDot2.kt")
        public void testAfterDot2() throws Exception {
            runTest("testData/handlers/basic/stringTemplate/AfterDot2.kt");
        }

        @TestMetadata("AfterDot3.kt")
        public void testAfterDot3() throws Exception {
            runTest("testData/handlers/basic/stringTemplate/AfterDot3.kt");
        }

        @TestMetadata("AfterDot4.kt")
        public void testAfterDot4() throws Exception {
            runTest("testData/handlers/basic/stringTemplate/AfterDot4.kt");
        }

        @TestMetadata("AfterThisDot.kt")
        public void testAfterThisDot() throws Exception {
            runTest("testData/handlers/basic/stringTemplate/AfterThisDot.kt");
        }

        @TestMetadata("GlobalVal.kt")
        public void testGlobalVal() throws Exception {
            runTest("testData/handlers/basic/stringTemplate/GlobalVal.kt");
        }

        @TestMetadata("GlobalValInCurlyBraces.kt")
        public void testGlobalValInCurlyBraces() throws Exception {
            runTest("testData/handlers/basic/stringTemplate/GlobalValInCurlyBraces.kt");
        }

        @TestMetadata("InsertCurlyBracesBeforeLetter.kt")
        public void testInsertCurlyBracesBeforeLetter() throws Exception {
            runTest("testData/handlers/basic/stringTemplate/InsertCurlyBracesBeforeLetter.kt");
        }

        @TestMetadata("NotEmptyPrefix.kt")
        public void testNotEmptyPrefix() throws Exception {
            runTest("testData/handlers/basic/stringTemplate/NotEmptyPrefix.kt");
        }

        @TestMetadata("Replace.kt")
        public void testReplace() throws Exception {
            runTest("testData/handlers/basic/stringTemplate/Replace.kt");
        }

        @TestMetadata("ValInObject.kt")
        public void testValInObject() throws Exception {
            runTest("testData/handlers/basic/stringTemplate/ValInObject.kt");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/handlers/basic/typeArgsForCall")
    public static class TypeArgsForCall extends AbstractBasicCompletionHandlerTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("AfterElse.kt")
        public void testAfterElse() throws Exception {
            runTest("testData/handlers/basic/typeArgsForCall/AfterElse.kt");
        }

        @TestMetadata("AfterElvis.kt")
        public void testAfterElvis() throws Exception {
            runTest("testData/handlers/basic/typeArgsForCall/AfterElvis.kt");
        }

        @TestMetadata("ExpectedTypeDoesNotHelp.kt")
        public void testExpectedTypeDoesNotHelp() throws Exception {
            runTest("testData/handlers/basic/typeArgsForCall/ExpectedTypeDoesNotHelp.kt");
        }

        @TestMetadata("ExpectedTypeDoesNotHelp2.kt")
        public void testExpectedTypeDoesNotHelp2() throws Exception {
            runTest("testData/handlers/basic/typeArgsForCall/ExpectedTypeDoesNotHelp2.kt");
        }

        @TestMetadata("ExplicitLambdaSignature.kt")
        public void testExplicitLambdaSignature() throws Exception {
            runTest("testData/handlers/basic/typeArgsForCall/ExplicitLambdaSignature.kt");
        }

        @TestMetadata("FunctionTypeParameter1.kt")
        public void testFunctionTypeParameter1() throws Exception {
            runTest("testData/handlers/basic/typeArgsForCall/FunctionTypeParameter1.kt");
        }

        @TestMetadata("FunctionTypeParameter2.kt")
        public void testFunctionTypeParameter2() throws Exception {
            runTest("testData/handlers/basic/typeArgsForCall/FunctionTypeParameter2.kt");
        }

        @TestMetadata("FunctionTypeParameter3.kt")
        public void testFunctionTypeParameter3() throws Exception {
            runTest("testData/handlers/basic/typeArgsForCall/FunctionTypeParameter3.kt");
        }

        @TestMetadata("HasExpectedType.kt")
        public void testHasExpectedType() throws Exception {
            runTest("testData/handlers/basic/typeArgsForCall/HasExpectedType.kt");
        }

        @TestMetadata("NotAllTypeArgumentsFromParameters.kt")
        public void testNotAllTypeArgumentsFromParameters() throws Exception {
            runTest("testData/handlers/basic/typeArgsForCall/NotAllTypeArgumentsFromParameters.kt");
        }

        @TestMetadata("ReplaceByTab1.kt")
        public void testReplaceByTab1() throws Exception {
            runTest("testData/handlers/basic/typeArgsForCall/ReplaceByTab1.kt");
        }

        @TestMetadata("ReplaceByTab2.kt")
        public void testReplaceByTab2() throws Exception {
            runTest("testData/handlers/basic/typeArgsForCall/ReplaceByTab2.kt");
        }

        @TestMetadata("Simple.kt")
        public void testSimple() throws Exception {
            runTest("testData/handlers/basic/typeArgsForCall/Simple.kt");
        }

        @TestMetadata("TypeArgumentsFromParameters.kt")
        public void testTypeArgumentsFromParameters() throws Exception {
            runTest("testData/handlers/basic/typeArgsForCall/TypeArgumentsFromParameters.kt");
        }
    }
}
