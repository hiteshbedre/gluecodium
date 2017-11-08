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

package com.here.ivi.api.generator.swift;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

import com.here.ivi.api.common.CollectionsHelper;
import com.here.ivi.api.generator.baseapi.CppCommentParser;
import com.here.ivi.api.generator.cbridge.CBridgeNameRules;
import com.here.ivi.api.generator.common.AbstractFrancaCommentParser;
import com.here.ivi.api.model.franca.DefinedBy;
import com.here.ivi.api.model.franca.FrancaDeploymentModel;
import com.here.ivi.api.model.rules.InstanceRules;
import com.here.ivi.api.model.swift.*;
import com.here.ivi.api.test.MockContextStack;
import java.util.List;
import org.franca.core.franca.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({
  SwiftTypeMapper.class,
  CppCommentParser.class,
  SwiftNameRules.class,
  CBridgeNameRules.class,
  InstanceRules.class,
  DefinedBy.class
})
public class SwiftModelBuilderTest {

  private static final String PARAM_NAME = "someParamName";
  private static final String FUNCTION_NAME = "someFunctionName";
  private static final String STRUCT_NAME = "someStruct";
  private static final String COMMENT = "some comment on model element";
  private static final String ATTRIBUTE_NAME = "someAttributeName";
  private static final String FIELD_NAME = "flowers";

  private final MockContextStack<SwiftModelElement> contextStack = new MockContextStack<>();

  @Mock private FrancaDeploymentModel deploymentModel;

  @Mock private AbstractFrancaCommentParser.Comments comments;
  @Mock private FMethod francaMethod;
  @Mock private FArgument francaArgument;
  @Mock private FTypeCollection francaTypeCollection;
  @Mock private FTypeRef francaTypeRef;
  @Mock private FField francaField;
  @Mock private FTypeDef francaTypeDef;
  @Mock private FAttribute francaAttribute;

  private final SwiftType swiftType = new SwiftType("VerySwiftType");
  private final SwiftValue swiftValue = new SwiftValue("");

  private SwiftModelBuilder modelBuilder;

  @Before
  public void setUp() {
    mockStatic(
        SwiftTypeMapper.class,
        CppCommentParser.class,
        SwiftNameRules.class,
        CBridgeNameRules.class,
        InstanceRules.class,
        DefinedBy.class);
    initMocks(this);

    when(francaArgument.getType()).thenReturn(francaTypeRef);
    when(CppCommentParser.parse(any(FMethod.class))).thenReturn(comments);
    when(CppCommentParser.parse(any(FInterface.class))).thenReturn(comments);
    when(CppCommentParser.parse(any(FEnumerator.class))).thenReturn(comments);
    when(CppCommentParser.parse(any(FEnumerationType.class))).thenReturn(comments);
    when(comments.getMainBodyText()).thenReturn(COMMENT);

    when(deploymentModel.isStatic(any())).thenReturn(true);
    when(SwiftNameRules.getParameterName(any())).thenReturn(PARAM_NAME);
    when(SwiftNameRules.getMethodName(any())).thenReturn(FUNCTION_NAME);

    when(francaArgument.getName()).thenReturn(PARAM_NAME);
    when(francaField.getName()).thenReturn(FIELD_NAME);

    when(francaTypeDef.getName()).thenReturn("definite");
    when(francaAttribute.getName()).thenReturn(ATTRIBUTE_NAME);

    modelBuilder = new SwiftModelBuilder(contextStack, deploymentModel);
  }

  @Test
  public void finishBuildingInputArgumentReturnsCreatedParams() {
    contextStack.injectResult(swiftType);

    modelBuilder.finishBuildingInputArgument(francaArgument);

    List<SwiftParameter> params = getResults(SwiftParameter.class);
    assertEquals(1, params.size());
    assertEquals(PARAM_NAME, params.get(0).variableName);
    assertSame(swiftType, params.get(0).type);
  }

  @Test
  public void finishBuildingInputArgumentCreatesGenericParameters() {
    contextStack.injectResult(new SwiftArray(swiftType));

    modelBuilder.finishBuildingInputArgument(francaArgument);

    SwiftGenericParameter genericParameter =
        modelBuilder.getFinalResult(SwiftGenericParameter.class);
    assertNotNull(genericParameter);

    assertEquals(PARAM_NAME, genericParameter.name);
    assertEquals(SwiftModelBuilder.COLLECTION_TYPE_NAME, genericParameter.typeName);
    assertEquals(1, genericParameter.constraints.size());

    SwiftGenericParameter.Constraint constraint = genericParameter.constraints.get(0);
    assertEquals(PARAM_NAME + "." + SwiftModelBuilder.ELEMENT_FIELD_NAME, constraint.name);
    assertEquals(swiftType.name, constraint.typeName);
    assertFalse(constraint.isProtocol);
  }

  @Test
  public void finishBuildingCreatesMethodWithArrayOfArraysParam() {
    contextStack.injectResult(new SwiftArray(new SwiftArray(swiftType)));

    modelBuilder.finishBuildingInputArgument(francaArgument);

    SwiftGenericParameter genericParameter =
        modelBuilder.getFinalResult(SwiftGenericParameter.class);
    assertNotNull(genericParameter);
    assertEquals(2, genericParameter.constraints.size());

    SwiftGenericParameter.Constraint constraint1 = genericParameter.constraints.get(0);
    assertEquals(PARAM_NAME + "." + SwiftModelBuilder.ELEMENT_FIELD_NAME, constraint1.name);
    assertEquals(SwiftModelBuilder.COLLECTION_TYPE_NAME, constraint1.typeName);
    assertTrue(constraint1.isProtocol);

    SwiftGenericParameter.Constraint constraint2 = genericParameter.constraints.get(1);
    assertEquals(constraint1.name + "." + SwiftModelBuilder.ELEMENT_FIELD_NAME, constraint2.name);
    assertEquals(swiftType.name, constraint2.typeName);
    assertFalse(constraint2.isProtocol);
  }

  @Test
  public void finishBuildingOutputArgumentReturnsCreatedParam() {
    contextStack.injectResult(swiftType);

    modelBuilder.finishBuildingOutputArgument(francaArgument);

    List<SwiftParameter> params = getResults(SwiftParameter.class);
    assertEquals(1, params.size());
    assertEquals(PARAM_NAME, params.get(0).variableName);
    assertSame(swiftType, params.get(0).type);
  }

  @Test
  public void finishBuildingOutputArgumentMarksStructAsOptional() {
    contextStack.injectResult(new SwiftType("Foo", SwiftType.TypeCategory.STRUCT));

    modelBuilder.finishBuildingOutputArgument(francaArgument);

    List<SwiftParameter> params = getResults(SwiftParameter.class);
    assertTrue(params.get(0).type.optional);
  }

  @Test
  public void finishBuildingOutputArgumentMarksStringAsOptional() {
    contextStack.injectResult(new SwiftType("Foo", SwiftType.TypeCategory.BUILTIN_STRING));

    modelBuilder.finishBuildingOutputArgument(francaArgument);

    List<SwiftParameter> params = getResults(SwiftParameter.class);
    assertTrue(params.get(0).type.optional);
  }

  @Test
  public void finishBuildingMethodReadsName() {
    modelBuilder.finishBuilding(francaMethod);

    List<SwiftMethod> methods = getResults(SwiftMethod.class);
    assertEquals(1, methods.size());
    SwiftMethod function = methods.get(0);
    assertEquals(FUNCTION_NAME, function.name);

    verifyStatic();
    SwiftNameRules.getMethodName(francaMethod);
  }

  @Test
  public void finishBuildingMethodOmitsSelector() {
    when(francaMethod.getSelector()).thenReturn("selective");

    modelBuilder.finishBuilding(francaMethod);

    List<SwiftMethod> methods = getResults(SwiftMethod.class);
    assertEquals(1, methods.size());
    SwiftMethod function = methods.get(0);
    assertEquals(FUNCTION_NAME, function.name);
  }

  @Test
  public void finishBuildingMethodCreatesStaticMethod() {
    when(deploymentModel.isStatic(francaMethod)).thenReturn(true);

    modelBuilder.finishBuilding(francaMethod);

    List<SwiftMethod> methods = getResults(SwiftMethod.class);
    assertEquals(1, methods.size());
    assertTrue("Method is marked as static", methods.get(0).isStatic);
  }

  @Test
  public void finishBuildingMethodCreatesNonStaticMethod() {
    when(deploymentModel.isStatic(francaMethod)).thenReturn(false);

    modelBuilder.finishBuilding(francaMethod);

    List<SwiftMethod> methods = getResults(SwiftMethod.class);
    assertEquals(1, methods.size());
    assertFalse("Method is marked as non-static", methods.get(0).isStatic);
  }

  @Test
  public void finishBuildingCreatesMethodWithoutParams() {
    modelBuilder.finishBuilding(francaMethod);

    List<SwiftMethod> methods = getResults(SwiftMethod.class);
    assertEquals(1, methods.size());
    SwiftMethod function = methods.get(0);
    assertEquals(SwiftType.VOID, function.returnType);
    assertEquals(0, function.parameters.size());
  }

  @Test
  public void finishBuildingCreatesMethodWithParam() {
    SwiftInParameter param = new SwiftInParameter(PARAM_NAME, swiftType);
    contextStack.injectResult(param);

    modelBuilder.finishBuilding(francaMethod);

    List<SwiftMethod> methods = getResults(SwiftMethod.class);
    assertEquals(1, methods.size());
    SwiftMethod method = methods.get(0);
    assertEquals(1, method.parameters.size());
    assertSame(param, method.parameters.get(0));
  }

  @Test
  public void finishBuildingMethodReadsGenericParameters() {
    SwiftGenericParameter genericParameter = new SwiftGenericParameter("foo", "bar");
    contextStack.injectResult(genericParameter);

    modelBuilder.finishBuilding(francaMethod);

    List<SwiftMethod> methods = getResults(SwiftMethod.class);
    assertEquals(1, methods.size());
    SwiftMethod method = methods.get(0);
    assertEquals(1, method.genericParameters.size());
    assertSame(genericParameter, method.genericParameters.get(0));
  }

  @Test
  public void finishBuildingCreatesTypesFromTypeCollection() {
    SwiftContainerType struct = new SwiftContainerType(STRUCT_NAME);
    SwiftEnum swiftEnum = SwiftEnum.builder("").build();
    contextStack.injectResult(struct);
    contextStack.injectResult(swiftEnum);

    modelBuilder.finishBuilding(francaTypeCollection);

    List<SwiftFile> files = getResults(SwiftFile.class);
    assertEquals(1, files.size());
    SwiftFile file = files.get(0);
    assertEquals("There should be no classes inside file", 0, file.classes.size());
    assertEquals("There should be one struct inside file", 1, file.structs.size());
    assertSame(struct, file.structs.get(0));
    assertEquals("There should be one enum inside file", 1, file.enums.size());
    assertSame(swiftEnum, file.enums.get(0));
  }

  @Test
  public void finishBuildingCreatesCValuesOutOfExpressions() {
    FExpression francaExpression = mock(FExpression.class);
    when(SwiftTypeMapper.mapType(any(FExpression.class))).thenReturn(swiftValue);

    modelBuilder.finishBuilding(francaExpression);

    List<SwiftValue> values = getResults(SwiftValue.class);
    assertEquals("Should be on value stored", 1, values.size());
    assertSame(swiftValue, values.get(0));
  }

  @Test
  public void finishBuildingCreatesEnumItemWithoutValue() {
    FEnumerator francaEnumerator = mock(FEnumerator.class);

    modelBuilder.finishBuilding(francaEnumerator);

    List<SwiftEnumItem> enumItems = getResults(SwiftEnumItem.class);
    assertEquals("Should be 1 enum item created", 1, enumItems.size());
    SwiftEnumItem enumItem = enumItems.get(0);
    assertNull("Enum item should have not value set", enumItem.value);
  }

  @Test
  public void finishBuildingCreatesEnumItemWithValue() {
    contextStack.injectResult(swiftValue);
    FEnumerator enumerator = mock(FEnumerator.class);

    modelBuilder.finishBuilding(enumerator);

    List<SwiftEnumItem> enumItems = getResults(SwiftEnumItem.class);
    assertEquals("Should be 1 enum item created", 1, enumItems.size());
    SwiftEnumItem enumItem = enumItems.get(0);
    assertSame(swiftValue, enumItem.value);
  }

  @Test
  public void finishBuildingCreatesSwiftEnum() {
    SwiftEnumItem swiftEnumItem = SwiftEnumItem.builder("").build();
    contextStack.injectResult(swiftEnumItem);
    FEnumerationType enumerationType = mock(FEnumerationType.class);
    when(SwiftNameRules.getEnumTypeName(any(), eq(deploymentModel))).thenReturn("SWIFT_NAME");

    modelBuilder.finishBuilding(enumerationType);

    List<SwiftEnum> enums = getResults(SwiftEnum.class);
    assertEquals("Should be 1 enum created", 1, enums.size());
    SwiftEnum enumType = enums.get(0);
    assertEquals("SWIFT_NAME", enumType.name);
    assertEquals("should be 1 enum item created", 1, enumType.items.size());
    assertSame(
        "Enum item inside enum type should be on injected into model",
        swiftEnumItem,
        enumType.items.get(0));
  }

  @Test
  public void finishBuildingFrancaFieldReadsName() {
    when(SwiftNameRules.getFieldName(eq(FIELD_NAME))).thenReturn("SwiftFieldName");
    modelBuilder.finishBuilding(francaField);

    List<SwiftField> swiftFields = getResults(SwiftField.class);
    assertEquals("Should be 1 field item created", 1, swiftFields.size());
    assertEquals("SwiftFieldName", swiftFields.get(0).name);
  }

  @Test
  public void finishBuildingFrancaFieldReadsType() {
    contextStack.injectResult(swiftType);

    modelBuilder.finishBuilding(francaField);

    List<SwiftField> swiftFields = getResults(SwiftField.class);
    assertEquals("Should be 1 field item created", 1, swiftFields.size());
    assertSame(swiftType, swiftFields.get(0).type);
  }

  @Test
  public void finishBuildingFrancaTypeDefDoesNotReadInstance() {
    when(InstanceRules.isInstanceId(francaTypeDef)).thenReturn(true);

    modelBuilder.finishBuilding(francaTypeDef);

    assertTrue(
        "Instance typedef should not be added to results",
        getResults(SwiftTypeDef.class).isEmpty());
  }

  @Test
  public void finishBuildingFrancaTypeDefReadsName() {
    when(SwiftNameRules.getTypeDefName(eq(francaTypeDef), any())).thenReturn("definite");

    modelBuilder.finishBuilding(francaTypeDef);

    List<SwiftTypeDef> swiftTypeDefs = getResults(SwiftTypeDef.class);
    assertEquals("Should be 1 field item created", 1, swiftTypeDefs.size());
    assertEquals("definite", swiftTypeDefs.get(0).name);
  }

  @Test
  public void finishBuildingFrancaTypeDefReadsType() {
    contextStack.injectResult(swiftType);

    modelBuilder.finishBuilding(francaTypeDef);

    List<SwiftTypeDef> swiftTypeDefs = getResults(SwiftTypeDef.class);
    assertEquals("Should be 1 field item created", 1, swiftTypeDefs.size());
    assertSame(swiftType, swiftTypeDefs.get(0).type);
  }

  @Test
  public void finishBuildingFrancaTypeRef() {
    when(InstanceRules.isInstanceId(francaTypeRef)).thenReturn(false);
    when(SwiftTypeMapper.mapType(any(FTypeRef.class), any())).thenReturn(swiftType);

    modelBuilder.finishBuilding(francaTypeRef);

    List<SwiftType> swiftTypes = getResults(SwiftType.class);
    assertEquals("Should be 1 type item created", 1, swiftTypes.size());
    assertSame(swiftType, swiftTypes.get(0));
    PowerMockito.verifyStatic();
    SwiftTypeMapper.mapType(francaTypeRef, deploymentModel);
  }

  @Test
  public void finishBuildingFrancaInstanceTypeRef() {
    when(francaTypeRef.getDerived()).thenReturn(francaTypeDef);
    when(SwiftTypeMapper.mapType(francaTypeRef, deploymentModel)).thenReturn(swiftType);
    when(InstanceRules.isInstanceId(francaTypeRef)).thenReturn(true);
    when(deploymentModel.isInterface(any())).thenReturn(false);

    modelBuilder.finishBuilding(francaTypeRef);

    List<SwiftType> swiftTypes = getResults(SwiftType.class);
    assertEquals("Should be 1 type item created", 1, swiftTypes.size());
    assertSame(swiftType, swiftTypes.get(0));
    PowerMockito.verifyStatic();
    SwiftTypeMapper.mapType(francaTypeRef, deploymentModel);
  }

  @Test
  public void finishBuildingFrancaInterfaceInstanceTypeRef() {
    when(francaTypeRef.getDerived()).thenReturn(francaTypeDef);
    when(SwiftTypeMapper.mapType(francaTypeRef, deploymentModel)).thenReturn(swiftType);
    when(InstanceRules.isInstanceId(francaTypeRef)).thenReturn(true);
    when(deploymentModel.isInterface(any())).thenReturn(true);

    modelBuilder.finishBuilding(francaTypeRef);

    List<SwiftType> swiftTypes = getResults(SwiftType.class);
    assertEquals("Should be 1 type item created", 1, swiftTypes.size());
    assertEquals(swiftType.name, swiftTypes.get(0).name);
    assertEquals(swiftType.implementingClass, swiftTypes.get(0).implementingClass);
    PowerMockito.verifyStatic();
    SwiftTypeMapper.mapType(francaTypeRef, deploymentModel);
  }

  @Test
  public void finishBuildingCreatesWritableAttribute() {
    prepareAttributeForTest();

    modelBuilder.finishBuilding(francaAttribute);

    SwiftProperty property = verifyAndGetProperty();
    assertSame(swiftType, property.type);
    assertEquals(ATTRIBUTE_NAME, property.name);
    assertFalse(property.readonly);
  }

  @Test
  public void finishBuildingCreatesReadonlyAttribute() {
    prepareAttributeForTest();
    when(francaAttribute.isReadonly()).thenReturn(true);

    modelBuilder.finishBuilding(francaAttribute);

    SwiftProperty property = verifyAndGetProperty();
    assertTrue(property.readonly);
  }

  @Test
  public void finishBuildingOutputArgumentDoesNotChangeStringType() {
    contextStack.injectResult(SwiftType.STRING);

    modelBuilder.finishBuildingOutputArgument(francaArgument);

    assertFalse(SwiftType.STRING.optional);
  }

  private void prepareAttributeForTest() {
    contextStack.injectResult(swiftType);
    when(SwiftNameRules.getPropertyName(any())).thenReturn(ATTRIBUTE_NAME);
  }

  private SwiftProperty verifyAndGetProperty() {
    List<SwiftProperty> properties = getResults(SwiftProperty.class);
    assertEquals("Should be one property created", 1, properties.size());
    return properties.get(0);
  }

  private <T extends SwiftModelElement> List<T> getResults(Class<T> clazz) {
    return CollectionsHelper.getAllOfType(contextStack.getParentContext().previousResults, clazz);
  }
}
