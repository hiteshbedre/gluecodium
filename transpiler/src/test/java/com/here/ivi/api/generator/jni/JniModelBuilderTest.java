/*
 * Copyright (C) 2017 HERE Global B.V. and its affiliate(s). All rights reserved.
 *
 * This software, including documentation, is protected by copyright controlled by
 * HERE Global B.V. All rights are reserved. Copying, including reproducing, storing,
 * adapting or translating, any or all of this material requires the prior written
 * consent of HERE Global B.V. This material also contains confidential information,
 * which may not be disclosed to others without prior written consent of HERE Global B.V.
 *
 */

package com.here.ivi.api.generator.jni;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.here.ivi.api.common.CollectionsHelper;
import com.here.ivi.api.generator.cpp.CppModelBuilder;
import com.here.ivi.api.generator.java.JavaModelBuilder;
import com.here.ivi.api.model.common.InstanceRules;
import com.here.ivi.api.model.cpp.*;
import com.here.ivi.api.model.franca.FrancaDeploymentModel;
import com.here.ivi.api.model.java.*;
import com.here.ivi.api.model.jni.*;
import com.here.ivi.api.test.ArrayEList;
import com.here.ivi.api.test.MockContextStack;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.franca.core.franca.*;
import org.franca.core.franca.FTypeDef;
import org.franca.core.franca.FTypeRef;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(InstanceRules.class)
public class JniModelBuilderTest {

  private static final String JAVA_CLASS_NAME = "jAvaClazz";
  private static final String JAVA_INTERFACE_NAME = "javaFAce";
  private static final String CPP_CLASS_NAME = "cPpClass";

  private static final String CPP_VOID_METHOD_NAME = "cPpWork3R_vOid";
  private static final String CPP_INT_METHOD_NAME = "cPpWork3R_iNt";

  private static final String JAVA_VOID_METHOD_NAME = "fancyMEthoD_v0id";
  private static final String JAVA_INT_METHOD_NAME = "fancyMEthoD_integer";

  private static final String BASE_NAME_PARAMETER = "theParam";
  private static final String TYPE_COLLECTION_NAME = "TestTypeCollection";

  private static final List<String> JAVA_PACKAGES = Arrays.asList("my", "java", "test");
  private static final List<String> CPP_NAMESPACE_MEMBERS =
      Arrays.asList("my", "cpp", "stuffs", "namespace");

  @Mock private FrancaDeploymentModel deploymentModel;
  @Mock private FInterface francaInterface;
  @Mock private FTypeCollection francaTypeCollection;
  @Mock private FMethod francaMethod;
  @Mock private FArgument francaArgument;
  @Mock private FStructType francaStructType;
  @Mock private FEnumerationType francaEnumType;
  @Mock private FEnumerator francaEnumerator;
  @Mock private FField francaField;
  @Mock private FModel fModel;
  @Mock private FAttribute francaAttribute;

  @Mock private JavaModelBuilder javaBuilder;
  @Mock private CppModelBuilder cppBuilder;

  private final JavaClass javaClass = new JavaClass(JAVA_CLASS_NAME);
  private final CppClass cppClass = new CppClass(CPP_CLASS_NAME);
  private final JavaEnum javaEnum = new JavaEnum(JAVA_CLASS_NAME);
  private final CppEnum cppEnum = CppEnum.create(CPP_CLASS_NAME);
  private final JavaCustomType javaCustomType = new JavaCustomType(JAVA_CLASS_NAME);
  private final CppComplexTypeRef cppCustomType =
      new CppComplexTypeRef.Builder(CPP_CLASS_NAME).build();

  private final MockContextStack<JniElement> contextStack = new MockContextStack<>();

  private final JniParameter jniParameter = new JniParameter(BASE_NAME_PARAMETER, null);
  private final JavaMethod javaGetter = new JavaMethod("getFoo", new JavaCustomType("FooType"));
  private final CppMethod cppGetter =
      new CppMethod.Builder("shootFoot").returnType(CppPrimitiveTypeRef.INT32).build();
  private final JavaMethod javaSetter = new JavaMethod("setFoo", JavaPrimitiveType.VOID);
  private final CppMethod cppSetter = new CppMethod.Builder("shootBothFeet").build();
  private final JniType jniType = JniType.createType(javaCustomType, cppCustomType);

  private JniModelBuilder modelBuilder;

  private final EList<FArgument> arguments = new ArrayEList<>();

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    PowerMockito.mockStatic(InstanceRules.class);

    modelBuilder = new JniModelBuilder(contextStack, deploymentModel, javaBuilder, cppBuilder);

    javaSetter.parameters.add(new JavaParameter(JavaPrimitiveType.INT, "value"));
    cppSetter.parameters.add(new CppParameter("value", CppPrimitiveTypeRef.INT8));

    when(javaBuilder.getFinalResult(any())).thenReturn(javaClass);
    when(cppBuilder.getFinalResult(any())).thenReturn(cppClass);
    when(francaMethod.getOutArgs()).thenReturn(arguments);

    when(francaInterface.eContainer()).thenReturn(fModel);
    when(francaTypeCollection.eContainer()).thenReturn(fModel);
    when(fModel.getName()).thenReturn(String.join(".", CPP_NAMESPACE_MEMBERS));
  }

  private static JavaMethod createJavaMethod() {
    JavaMethod javaMethod = new JavaMethod(JAVA_INT_METHOD_NAME, JavaPrimitiveType.INT);
    javaMethod.parameters.add(new JavaParameter(JavaPrimitiveType.INT, BASE_NAME_PARAMETER));
    return javaMethod;
  }

  private static CppMethod createCppMethod() {
    CppPrimitiveTypeRef cppPrimitiveType = CppPrimitiveTypeRef.INT8;
    CppParameter cppParameter = new CppParameter("", cppPrimitiveType);

    return new CppMethod.Builder(CPP_INT_METHOD_NAME)
        .parameter(cppParameter)
        .returnType(cppPrimitiveType)
        .build();
  }

  private JniMethod createJniMethod(JniContainer jniContainer) {

    JniMethod result = new JniMethod.Builder(JAVA_VOID_METHOD_NAME, CPP_VOID_METHOD_NAME).build();
    result.owningContainer = jniContainer;

    return result;
  }

  @Test
  public void finishBuildingFrancaMethodVoid() {
    //arrange
    when(javaBuilder.getFinalResult(any())).thenReturn(new JavaMethod(JAVA_VOID_METHOD_NAME));
    when(cppBuilder.getFinalResult(any()))
        .thenReturn(new CppMethod.Builder(CPP_VOID_METHOD_NAME).build());

    //act
    modelBuilder.finishBuilding(francaMethod);

    //assert
    JniMethod jniMethod = modelBuilder.getFinalResult(JniMethod.class);
    assertEquals(createJniMethod(null), jniMethod);
  }

  @Test
  public void finishBuildingFrancaMethodReadsJavaCppMethods() {
    JavaMethod javaMethod = createJavaMethod();
    CppMethod cppMethod = createCppMethod();
    when(javaBuilder.getFinalResult(any())).thenReturn(javaMethod);
    when(cppBuilder.getFinalResult(any())).thenReturn(cppMethod);

    modelBuilder.finishBuilding(francaMethod);

    JniMethod jniMethod = modelBuilder.getFinalResult(JniMethod.class);
    assertNotNull(jniMethod);
    assertEquals(javaMethod.name, jniMethod.javaMethodName);
    assertEquals(cppMethod.name, jniMethod.cppMethodName);
    assertEquals(javaMethod.returnType.name, jniMethod.returnType.javaName);
    assertEquals(cppMethod.returnType.name, jniMethod.returnType.cppName);
  }

  @Test
  public void finishBuildingFrancaMethodReadsJniParameters() {
    contextStack.injectResult(jniParameter);
    when(javaBuilder.getFinalResult(any())).thenReturn(createJavaMethod());
    when(cppBuilder.getFinalResult(any())).thenReturn(createCppMethod());

    modelBuilder.finishBuilding(francaMethod);

    JniMethod jniMethod = modelBuilder.getFinalResult(JniMethod.class);
    assertNotNull(jniMethod);
    assertEquals(1, jniMethod.parameters.size());
    assertEquals(jniParameter, jniMethod.parameters.get(0));
    assertFalse(jniMethod.isStatic);
  }

  @Test
  public void finishBuildingFrancaMethodReadsStaticMethods() {
    contextStack.injectResult(jniParameter);
    when(javaBuilder.getFinalResult(any())).thenReturn(createJavaMethod());
    CppMethod cppMethod = createCppMethod();
    cppMethod.specifiers.add(CppMethod.Specifier.STATIC);
    when(cppBuilder.getFinalResult(any())).thenReturn(cppMethod);

    modelBuilder.finishBuilding(francaMethod);

    JniMethod jniMethod = modelBuilder.getFinalResult(JniMethod.class);
    assertNotNull(jniMethod);
    assertTrue(jniMethod.isStatic);
  }

  @Test
  public void finishBuildingFrancaMethodReadsConstMethods() {
    contextStack.injectResult(jniParameter);
    when(javaBuilder.getFinalResult(any())).thenReturn(createJavaMethod());
    CppMethod cppMethod = createCppMethod();
    cppMethod.qualifiers.add(CppMethod.Qualifier.CONST);
    when(cppBuilder.getFinalResult(any())).thenReturn(cppMethod);

    modelBuilder.finishBuilding(francaMethod);

    JniMethod jniMethod = modelBuilder.getFinalResult(JniMethod.class);
    assertNotNull(jniMethod);
    assertTrue(jniMethod.isConst);
  }

  @Test
  public void finishBuildingFrancaMethodReadsSelector() {
    when(francaMethod.getSelector()).thenReturn("Foo");
    when(javaBuilder.getFinalResult(any())).thenReturn(createJavaMethod());
    when(cppBuilder.getFinalResult(any())).thenReturn(createCppMethod());

    modelBuilder.finishBuilding(francaMethod);

    JniMethod jniMethod = modelBuilder.getFinalResult(JniMethod.class);
    assertNotNull(jniMethod);
    assertTrue(jniMethod.isOverloaded);

    verify(francaMethod).getSelector();
  }

  @Test
  public void finishBuildingFrancaMethodReadsExceptionName() {
    JavaMethod javaMethod =
        new JavaMethod(
            JAVA_INT_METHOD_NAME,
            JavaPrimitiveType.INT,
            new JavaCustomType("FooException", JavaPackage.DEFAULT));
    javaMethod.parameters.add(new JavaParameter(JavaPrimitiveType.INT, BASE_NAME_PARAMETER));
    when(javaBuilder.getFinalResult(any())).thenReturn(javaMethod);
    when(cppBuilder.getFinalResult(any())).thenReturn(createCppMethod());

    modelBuilder.finishBuilding(francaMethod);

    JniMethod jniMethod = modelBuilder.getFinalResult(JniMethod.class);
    assertNotNull(jniMethod);
    assertEquals("com/here/android/FooException", jniMethod.exception.javaClassName);
  }

  @Test
  public void finishBuildingFrancaMethodReadsExceptionEnum() {
    JavaMethod javaMethod =
        new JavaMethod(
            JAVA_INT_METHOD_NAME,
            JavaPrimitiveType.INT,
            new JavaCustomType("FooException", JavaPackage.DEFAULT));
    javaMethod.parameters.add(new JavaParameter(JavaPrimitiveType.INT, BASE_NAME_PARAMETER));
    when(javaBuilder.getFinalResult(any())).thenReturn(javaMethod);
    when(cppBuilder.getFinalResult(any())).thenReturn(createCppMethod());
    contextStack.injectResult(jniType);

    modelBuilder.finishBuilding(francaMethod);

    JniMethod jniMethod = modelBuilder.getFinalResult(JniMethod.class);
    assertNotNull(jniMethod);
    assertEquals(jniType, jniMethod.exception.jniEnum);
  }

  @Test
  public void finishBuildingInstantiableFrancaInterface() {
    //arrange
    CppMethod nonStaticMethod = new CppMethod.Builder("nonStaticMethod").build();
    cppClass.methods.add(nonStaticMethod);

    //act
    modelBuilder.finishBuilding(francaInterface);

    //assert
    JniContainer jniContainer = modelBuilder.getFinalResult(JniContainer.class);
    assertNotNull(jniContainer);
    assertFalse(jniContainer.isInterface);
  }

  @Test
  public void finishBuildingInstantiableFrancaInterfaceReadsJavaCppClasses() {
    //arrange
    javaClass.javaPackage = new JavaPackage(JAVA_PACKAGES);

    //act
    modelBuilder.finishBuilding(francaInterface);

    //assert
    JniContainer jniContainer = modelBuilder.getFinalResult(JniContainer.class);
    assertNotNull(jniContainer);
    assertEquals(CPP_CLASS_NAME, jniContainer.cppName);
    assertEquals(JAVA_CLASS_NAME, jniContainer.javaName);
    assertEquals(CPP_NAMESPACE_MEMBERS, jniContainer.cppNameSpaces);
    assertEquals(JAVA_PACKAGES, jniContainer.javaPackages);
    assertFalse(jniContainer.isInterface);
  }

  @Test
  public void finishBuildingInstantiableFrancaInterfaceReadsJavaInterface() {
    JavaInterface javaInterface = new JavaInterface(JAVA_INTERFACE_NAME);
    javaInterface.javaPackage = new JavaPackage(JAVA_PACKAGES);
    when(javaBuilder.getFinalResult(JavaTopLevelElement.class)).thenReturn(javaInterface);
    when(javaBuilder.getFinalResult(JavaClass.class)).thenReturn(javaClass);

    modelBuilder.finishBuilding(francaInterface);

    JniContainer jniContainer = modelBuilder.getFinalResult(JniContainer.class);
    assertNotNull(jniContainer);
    assertFalse(jniContainer.isInterface);
    assertEquals(JAVA_CLASS_NAME, jniContainer.javaName);
    assertEquals(JAVA_INTERFACE_NAME, jniContainer.javaInterfaceName);
    assertEquals(JAVA_PACKAGES, jniContainer.javaPackages);
  }

  @Test
  public void finishBuildingInstantiableFrancaInterfaceReadsMethods() {
    contextStack.injectResult(createJniMethod(null));
    javaClass.javaPackage = new JavaPackage(JAVA_PACKAGES);

    //act
    modelBuilder.finishBuilding(francaInterface);

    //assert
    JniContainer jniContainer = modelBuilder.getFinalResult(JniContainer.class);
    assertNotNull(jniContainer);
    assertFalse(jniContainer.methods.isEmpty());
    assertFalse(jniContainer.isInterface);
    assertEquals(createJniMethod(jniContainer), jniContainer.methods.get(0));
  }

  @Test
  public void finishBuildingFrancaInterface() {
    //arrange
    when(deploymentModel.isInterface(francaInterface)).thenReturn(true);

    //act
    modelBuilder.finishBuilding(francaInterface);

    //assert
    JniContainer jniContainer = modelBuilder.getFinalResult(JniContainer.class);
    assertNotNull(jniContainer);
    assertTrue(jniContainer.isInterface);
  }

  @Test
  public void finishBuildingFrancaInterfaceReadsJavaCppClasses() {
    //arrange
    when(deploymentModel.isInterface(francaInterface)).thenReturn(true);
    javaClass.javaPackage = new JavaPackage(JAVA_PACKAGES);

    //act
    modelBuilder.finishBuilding(francaInterface);

    //assert
    JniContainer jniContainer = modelBuilder.getFinalResult(JniContainer.class);
    assertNotNull(jniContainer);
    assertTrue(jniContainer.isInterface);
    assertEquals(CPP_CLASS_NAME, jniContainer.cppName);
    assertEquals(JAVA_CLASS_NAME, jniContainer.javaName);
    assertEquals(CPP_NAMESPACE_MEMBERS, jniContainer.cppNameSpaces);
    assertEquals(JAVA_PACKAGES, jniContainer.javaPackages);
  }

  @Test
  public void finishBuildingFrancaInterfaceReadsJavaInterface() {
    when(deploymentModel.isInterface(francaInterface)).thenReturn(true);
    JavaInterface javaInterface = new JavaInterface(JAVA_INTERFACE_NAME);
    javaInterface.javaPackage = new JavaPackage(JAVA_PACKAGES);
    when(javaBuilder.getFinalResult(JavaTopLevelElement.class)).thenReturn(javaInterface);
    when(javaBuilder.getFinalResult(JavaClass.class)).thenReturn(javaClass);

    modelBuilder.finishBuilding(francaInterface);

    JniContainer jniContainer = modelBuilder.getFinalResult(JniContainer.class);
    assertNotNull(jniContainer);
    assertTrue(jniContainer.isInterface);
    assertEquals(JAVA_CLASS_NAME, jniContainer.javaName);
    assertEquals(JAVA_INTERFACE_NAME, jniContainer.javaInterfaceName);
    assertEquals(JAVA_PACKAGES, jniContainer.javaPackages);
  }

  @Test
  public void finishBuildingInputArgumentReadsJavaCppParameters() {
    JavaParameter javaParameter = new JavaParameter(javaCustomType, "relative");
    CppParameter cppParameter =
        new CppParameter("absolute", new CppComplexTypeRef.Builder(CPP_CLASS_NAME).build());
    when(javaBuilder.getFinalResult(any())).thenReturn(javaParameter);
    when(cppBuilder.getFinalResult(any())).thenReturn(cppParameter);
    when(francaArgument.getType()).thenReturn(mock(FTypeRef.class));

    modelBuilder.finishBuildingInputArgument(francaArgument);

    JniParameter resultParameter = modelBuilder.getFinalResult(JniParameter.class);
    assertNotNull(resultParameter);
    assertEquals(javaParameter.name, resultParameter.name);
    assertEquals(javaParameter.type.name, resultParameter.type.javaName);
    assertEquals(cppParameter.type.name, resultParameter.type.cppName);
  }

  @Test
  public void finishBuildingInputArgumentReadsInstance() {
    JavaParameter javaParameter = new JavaParameter(javaCustomType, "relative");
    CppParameter cppParameter =
        new CppParameter("absolute", new CppComplexTypeRef.Builder(CPP_CLASS_NAME).build());
    when(javaBuilder.getFinalResult(any())).thenReturn(javaParameter);
    when(cppBuilder.getFinalResult(any())).thenReturn(cppParameter);

    FTypeRef fTypeRef = mock(FTypeRef.class);
    FTypeDef fTypeDef = mock(FTypeDef.class);
    when(fTypeRef.getDerived()).thenReturn(fTypeDef);
    when(francaArgument.getType()).thenReturn(fTypeRef);
    when(InstanceRules.isInstanceId(fTypeDef)).thenReturn(true);

    modelBuilder.finishBuildingInputArgument(francaArgument);

    JniParameter resultParameter = modelBuilder.getFinalResult(JniParameter.class);
    assertNotNull(resultParameter);
    assertTrue(resultParameter.type.isInstance);
  }

  @Test
  public void finishBuildingFrancaStructReadsJavaCppClasses() {
    CppStruct cppStruct = new CppStruct(CPP_CLASS_NAME);
    when(cppBuilder.getFinalResult(any())).thenReturn(cppStruct);

    modelBuilder.finishBuilding(francaStructType);

    JniStruct jniStruct = modelBuilder.getFinalResult(JniStruct.class);
    assertNotNull(jniStruct);
    assertEquals(javaClass, jniStruct.javaClass);
    assertEquals(cppStruct, jniStruct.cppStruct);
    assertEquals(javaClass.javaPackage, jniStruct.javaPackage);
  }

  @Test
  public void finishBuildingFrancaStructReadsFields() {
    JniField jniField =
        new JniField(
            new JavaField(javaCustomType, BASE_NAME_PARAMETER, new JavaValue(javaCustomType)),
            new CppField(cppCustomType, BASE_NAME_PARAMETER),
            null);
    contextStack.injectResult(jniField);
    when(javaBuilder.getFinalResult(any())).thenReturn(javaClass);
    when(cppBuilder.getFinalResult(any())).thenReturn(null);

    modelBuilder.finishBuilding(francaStructType);

    JniStruct jniStruct = modelBuilder.getFinalResult(JniStruct.class);
    assertNotNull(jniStruct);
    assertFalse(jniStruct.fields.isEmpty());
    assertEquals(jniField, jniStruct.fields.get(0));
  }

  @Test
  public void finishBuildingFrancaStructReadsParentFields() {
    JniField jniParentField =
        new JniField(
            new JavaField(javaCustomType, "ancient_nonsense", new JavaValue(javaCustomType)),
            new CppField(cppCustomType, "ancient_nonsense"),
            null);
    JniStruct jniParentStruct =
        new JniStruct(javaClass, null, Collections.singletonList(jniParentField));
    contextStack.injectResult(jniParentStruct);
    JniField jniField =
        new JniField(
            new JavaField(javaCustomType, BASE_NAME_PARAMETER, new JavaValue(javaCustomType)),
            new CppField(cppCustomType, BASE_NAME_PARAMETER),
            null);
    contextStack.injectResult(jniField);
    when(javaBuilder.getFinalResult(any())).thenReturn(javaClass);
    when(cppBuilder.getFinalResult(any())).thenReturn(null);

    modelBuilder.finishBuilding(francaStructType);

    JniStruct jniStruct = modelBuilder.getFinalResult(JniStruct.class);
    assertNotNull(jniStruct);
    assertEquals(2, jniStruct.fields.size());
    assertEquals(jniParentField, jniStruct.fields.get(0));
    assertEquals(jniField, jniStruct.fields.get(1));
  }

  @Test
  public void finishBuildingFrancaFieldReadsJavaCppFields() {
    JavaField javaField =
        new JavaField(javaCustomType, BASE_NAME_PARAMETER, new JavaValue(javaCustomType));
    CppField cppField = new CppField(cppCustomType, CPP_CLASS_NAME);
    when(javaBuilder.getFinalResult(any())).thenReturn(javaField);
    when(cppBuilder.getFinalResult(any())).thenReturn(cppField);

    modelBuilder.finishBuilding(francaField);

    JniField jniField = modelBuilder.getFinalResult(JniField.class);
    assertNotNull(jniField);
    assertEquals(javaField, jniField.javaField);
    assertEquals(cppField, jniField.cppField);
  }

  @Test
  public void finishBuildingFrancaFieldReadsJniType() {
    JavaField javaField =
        new JavaField(javaCustomType, BASE_NAME_PARAMETER, new JavaValue(javaCustomType));
    CppField cppField = new CppField(cppCustomType, CPP_CLASS_NAME);
    when(javaBuilder.getFinalResult(any())).thenReturn(javaField);
    when(cppBuilder.getFinalResult(any())).thenReturn(cppField);
    contextStack.injectResult(jniType);

    modelBuilder.finishBuilding(francaField);

    JniField jniField = modelBuilder.getFinalResult(JniField.class);
    assertNotNull(jniField);
    assertEquals(jniType, jniField.type);
  }

  @Test
  public void finishBuildingFrancaTypeCollectionReadsStructs() {
    when(francaTypeCollection.getName()).thenReturn(TYPE_COLLECTION_NAME);
    JniStruct jniStruct = new JniStruct(javaClass, new CppStruct(CPP_CLASS_NAME), null);
    contextStack.injectResult(jniStruct);

    modelBuilder.finishBuilding(francaTypeCollection);

    JniContainer jniContainer = modelBuilder.getFinalResult(JniContainer.class);
    assertNotNull(jniContainer);
    assertFalse(jniContainer.structs.isEmpty());
    assertEquals(jniStruct.javaClass, jniContainer.structs.get(0).javaClass);
    String expectedNamespace = "my::cpp::stuffs::namespace";
    assertEquals(expectedNamespace, String.join("::", jniContainer.cppNameSpaces));
    assertNull(jniContainer.javaName);
    assertNull(jniContainer.cppName);
  }

  @Test
  public void finishBuildingFrancaTypeCollectionWithNoStruct() {
    when(francaTypeCollection.getName()).thenReturn(TYPE_COLLECTION_NAME);

    modelBuilder.finishBuilding(francaTypeCollection);
    JniContainer jniContainer = modelBuilder.getFinalResult(JniContainer.class);

    assertNotNull(jniContainer);
    assertTrue(jniContainer.structs.isEmpty());
    assertTrue(jniContainer.javaPackages.isEmpty());
    assertEquals("my.cpp.stuffs.namespace", String.join(".", jniContainer.cppNameSpaces));
    assertNull(jniContainer.javaName);
    assertNull(jniContainer.cppName);
  }

  @Test
  public void finishBuildingFrancaAttributeCreatesGetter() {
    when(javaBuilder.getFinalResults()).thenReturn(Arrays.asList(javaGetter, javaSetter));
    when(cppBuilder.getFinalResults()).thenReturn(Arrays.asList(cppGetter, cppSetter));

    modelBuilder.finishBuilding(francaAttribute);

    JniMethod jniMethod = modelBuilder.getFinalResult(JniMethod.class);
    assertNotNull(jniMethod);
    assertEquals(javaGetter.name, jniMethod.javaMethodName);
    assertEquals(cppGetter.name, jniMethod.cppMethodName);
    assertEquals(javaGetter.returnType.name, jniMethod.returnType.javaName);
    assertEquals(cppGetter.returnType.name, jniMethod.returnType.cppName);
    assertFalse(jniMethod.isStatic);
  }

  @Test
  public void finishBuildingFrancaAttributeCreatesSetter() {
    when(javaBuilder.getFinalResults()).thenReturn(Arrays.asList(javaGetter, javaSetter));
    when(cppBuilder.getFinalResults()).thenReturn(Arrays.asList(cppGetter, cppSetter));

    modelBuilder.finishBuilding(francaAttribute);

    List<JniMethod> methods =
        CollectionsHelper.getAllOfType(modelBuilder.getFinalResults(), JniMethod.class);
    assertEquals("Both a getter and a setter should be created", 2, methods.size());

    JniMethod jniMethod = methods.get(1);
    assertEquals(javaSetter.name, jniMethod.javaMethodName);
    assertEquals(cppSetter.name, jniMethod.cppMethodName);
    assertNull(jniMethod.returnType);
    assertFalse(jniMethod.isStatic);
  }

  @Test
  public void finishBuildingFrancaAttributeReadsParametersIntoSetter() {
    when(javaBuilder.getFinalResults()).thenReturn(Arrays.asList(javaGetter, javaSetter));
    when(cppBuilder.getFinalResults()).thenReturn(Arrays.asList(cppGetter, cppSetter));

    modelBuilder.finishBuilding(francaAttribute);

    List<JniMethod> methods =
        CollectionsHelper.getAllOfType(modelBuilder.getFinalResults(), JniMethod.class);
    assertEquals("Both a getter and a setter should be created", 2, methods.size());

    JniMethod jniMethod = methods.get(1);
    assertEquals(1, jniMethod.parameters.size());

    JniParameter setterParameter = jniMethod.parameters.get(0);
    assertEquals(javaSetter.parameters.get(0).name, setterParameter.name);
    assertEquals(javaSetter.parameters.get(0).type.name, setterParameter.type.javaName);
    assertEquals(cppSetter.parameters.get(0).type.name, setterParameter.type.cppName);
  }

  @Test
  public void finishBuildingFrancaAttributeReadonly() {
    when(francaAttribute.isReadonly()).thenReturn(true);
    when(javaBuilder.getFinalResults()).thenReturn(Collections.singletonList(javaGetter));
    when(cppBuilder.getFinalResults()).thenReturn(Collections.singletonList(cppGetter));

    modelBuilder.finishBuilding(francaAttribute);

    List<JniMethod> methods =
        CollectionsHelper.getAllOfType(modelBuilder.getFinalResults(), JniMethod.class);
    assertEquals("Only a getter should be created", 1, methods.size());

    JniMethod jniMethod = methods.get(0);
    assertEquals(javaGetter.name, jniMethod.javaMethodName);
    assertEquals(cppGetter.name, jniMethod.cppMethodName);
    assertEquals(javaGetter.returnType.name, jniMethod.returnType.javaName);
    assertEquals(cppGetter.returnType.name, jniMethod.returnType.cppName);
  }

  @Test
  public void finishBuildingFrancaEnumerationsReadsEnumerators() {
    // arrange
    contextStack.getParentContext().allowsTypeDefinitions = true;
    when(cppBuilder.getFinalResult(any())).thenReturn(cppEnum);
    when(javaBuilder.getFinalResult(any())).thenReturn(javaEnum);
    contextStack.injectResult(new JniEnumerator("oneJ", "oneC"));
    contextStack.injectResult(new JniEnumerator("twoJ", "twoC"));
    contextStack.injectResult(new JniEnumerator("threeJ", "threeC"));

    // act
    modelBuilder.finishBuilding(francaEnumType);

    // assert
    JniEnum jniEnum = modelBuilder.getFinalResult(JniEnum.class);
    assertNotNull(jniEnum);
    assertEquals(javaEnum.name, jniEnum.javaEnumName);
    assertEquals(cppEnum.name, jniEnum.cppEnumName);
    assertNotNull(jniEnum.enumerators);
    assertEquals(3, jniEnum.enumerators.size());
    assertEquals("oneC", jniEnum.enumerators.get(0).cppName);
    assertEquals("oneJ", jniEnum.enumerators.get(0).javaName);
    assertEquals("twoC", jniEnum.enumerators.get(1).cppName);
    assertEquals("twoJ", jniEnum.enumerators.get(1).javaName);
    assertEquals("threeC", jniEnum.enumerators.get(2).cppName);
    assertEquals("threeJ", jniEnum.enumerators.get(2).javaName);
  }

  @Test
  public void finishBuildingFrancaEnumerationsReadsTypeReferences() {
    contextStack.getParentContext().allowsTypeDefinitions = false;
    when(cppBuilder.getFinalResult(any())).thenReturn(cppCustomType);
    when(javaBuilder.getFinalResult(any())).thenReturn(javaCustomType);

    modelBuilder.finishBuilding(francaEnumType);

    JniType resultType = modelBuilder.getFinalResult(JniType.class);
    assertNotNull(resultType);
    assertEquals(javaCustomType.name, resultType.javaName);
    assertEquals(cppCustomType.name, resultType.cppName);
  }

  @Test
  public void finishBuildingFEnumerator() {

    // arrange
    when(cppBuilder.getFinalResult(any())).thenReturn(new CppEnumItem("cppEnumerator"));
    when(javaBuilder.getFinalResult(any())).thenReturn(new JavaEnumItem("javaEnumerator"));

    // act
    modelBuilder.finishBuilding(francaEnumerator);

    // assert
    JniEnumerator jniEnumItem = modelBuilder.getFinalResult(JniEnumerator.class);
    assertNotNull(jniEnumItem);
    assertEquals(jniEnumItem.cppName, "cppEnumerator");
    assertEquals(jniEnumItem.javaName, "javaEnumerator");
  }

  @Test
  public void finishBuildingFrancaTypeCollectionReadsEnums() {

    // arrange
    when(francaTypeCollection.getName()).thenReturn(TYPE_COLLECTION_NAME);
    JniEnum jniEnum =
        new JniEnum.Builder("MyJavaEnumName", "MyCppEnumName")
            .javaPackage(JavaPackage.DEFAULT)
            .build();
    contextStack.injectResult(jniEnum);

    // act
    modelBuilder.finishBuilding(francaTypeCollection);

    // assert
    JniContainer jniContainer = modelBuilder.getFinalResult(JniContainer.class);
    assertNotNull(jniContainer);
    assertFalse(jniContainer.enums.isEmpty());
    assertEquals(jniEnum, jniContainer.enums.get(0));
  }

  @Test
  public void finishBuildingFrancaInterfaceReadsEnums() {

    // arrange
    JniEnum jniEnum = new JniEnum.Builder("MyJavaEnumName", "MyCppEnumName").build();
    contextStack.injectResult(jniEnum);

    // act
    modelBuilder.finishBuilding(francaInterface);

    // assert
    JniContainer jniContainer = modelBuilder.getFinalResult(JniContainer.class);
    assertNotNull(jniContainer);
    assertFalse(jniContainer.enums.isEmpty());
    assertEquals(jniEnum, jniContainer.enums.get(0));
  }
}
