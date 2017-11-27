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

package com.here.ivi.api.generator.swift.templates;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

import com.here.ivi.api.generator.common.TemplateEngine;
import com.here.ivi.api.model.swift.*;
import com.here.ivi.api.model.swift.SwiftType.TypeCategory;
import com.here.ivi.api.model.swift.SwiftValue;
import com.here.ivi.api.test.TemplateComparator;
import com.here.ivi.api.test.TemplateComparison;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@SuppressWarnings("all")
public class SwiftFileTemplateTest {

  private static String generateFromClass(SwiftClass swiftClass) {
    SwiftFile file = new SwiftFile();
    file.classes.add(swiftClass);
    return generate(file);
  }

  private static String generate(SwiftFile file) {
    return TemplateEngine.render("swift/File", file);
  }

  @Test
  public void simpleInterfaceGeneration() {
    final SwiftClass swiftClass = SwiftClass.builder("ExampleClass").isInterface(true).build();
    swiftClass.implementsProtocols.add("ExampleClass");

    TemplateComparator.expect("public protocol ExampleClass : AnyObject {\n" + "}\n")
        .expect("internal class _ExampleClass: ExampleClass {\n" + "}\n")
        .build()
        .assertMatches(generateFromClass(swiftClass));
  }

  @Test
  public void getRefFunctionIsGenerated() {
    final SwiftClass swiftClass =
        SwiftClass.builder("ExampleClass")
            .isInterface(true)
            .cInstance("CInstance")
            .cInstanceRef("CInstanceReference")
            .functionTableName("ExampleFunctionTable")
            .build();
    swiftClass.implementsProtocols.add("ExampleClass");

    TemplateComparator.expect(
            "internal func getRef(_ ref: ExampleClass) -> RefHolder<CInstanceReference> {\n"
                + "    if let instanceReference = ref as? _ExampleClass {\n"
                + "        return RefHolder<CInstanceReference>(instanceReference.c_instance)\n"
                + "    }\n"
                + "    var functions = ExampleFunctionTable()\n"
                + "    functions.swift_pointer = Unmanaged<AnyObject>.passRetained(ref).toOpaque()\n"
                + "    functions.release = {swiftClass_pointer in\n"
                + "        if let swiftClass = swiftClass_pointer {\n"
                + "            Unmanaged<AnyObject>.fromOpaque(swiftClass).release()\n"
                + "        }\n"
                + "    }\n"
                + "    return RefHolder(ref: CInstance_createProxy(functions), release: CInstance_release)\n"
                + "}\n")
        .build()
        .assertMatches(generateFromClass(swiftClass));
  }

  @Test
  public void interfaceWithCommentGeneration() {
    SwiftClass swiftClass = SwiftClass.builder("ExampleClassWithComment").isInterface(true).build();
    swiftClass.implementsProtocols.add("ExampleClassWithComment");
    swiftClass.comment = "One really classy example";

    TemplateComparator.expect(
            "/**\n"
                + " * One really classy example\n"
                + " */\n"
                + "public protocol ExampleClassWithComment : AnyObject {\n"
                + "}\n")
        .expect("internal class _ExampleClassWithComment: ExampleClassWithComment {\n" + "}\n")
        .build()
        .assertMatches(generateFromClass(swiftClass));
  }

  @Test
  public void simpleMethodGeneration() {
    SwiftClass swiftClass =
        SwiftClass.builder("ExampleClass").nameSpace("myPackage").isInterface(true).build();
    SwiftMethod method =
        new SwiftMethod(
            "myMethod",
            Collections.singletonList(new SwiftParameter("parameter", new SwiftType("Int"))));
    swiftClass.methods.add(method);
    swiftClass.implementsProtocols.add("ExampleClass");
    method.returnType = new SwiftType("Int");
    method.cBaseName = "myPackage_ExampleClass_myMethod";

    TemplateComparator.expect(
            "public protocol ExampleClass : AnyObject {\n"
                + "    func myMethod(parameter: Int) -> Int\n"
                + "}\n")
        .expect(
            "internal class _ExampleClass: ExampleClass {\n"
                + "    public func myMethod(parameter: Int) -> Int {\n"
                + "        return myPackage_ExampleClass_myMethod(c_instance, parameter)\n"
                + "    }\n"
                + "}\n")
        .build()
        .assertMatches(generateFromClass(swiftClass));
  }

  @Test
  public void methodParameterDifferentInterfaceAndVariableName() {
    SwiftClass swiftClass = SwiftClass.builder("ExampleClass").isInterface(true).build();
    SwiftMethod method =
        new SwiftMethod(
            "myMethod",
            Collections.singletonList(
                new SwiftParameter(
                    "parameterInterfaceName", new SwiftType("Int"), "parameterVariableName")));
    method.cBaseName = "ExampleClass_myMethod";
    swiftClass.implementsProtocols.add("ExampleClass");
    swiftClass.methods.add(method);

    TemplateComparator.expect(
            "public protocol ExampleClass : AnyObject {\n"
                + "    func myMethod(parameterInterfaceName parameterVariableName: Int) -> Void\n"
                + "}\n")
        .expect(
            "internal class _ExampleClass: ExampleClass {\n"
                + "    public func myMethod(parameterInterfaceName parameterVariableName: Int) -> Void {\n"
                + "        return ExampleClass_myMethod(c_instance, parameterVariableName)\n"
                + "    }\n"
                + "}\n")
        .build()
        .assertMatches(generateFromClass(swiftClass));
  }

  @Test
  public void methodWithMultipleParameters() {
    SwiftClass swiftClass = SwiftClass.builder("ExampleClass").isInterface(true).build();
    swiftClass.implementsProtocols.add("ExampleClass");
    SwiftParameter parameterOne = new SwiftParameter("parameterOne", new SwiftType("Int"));
    SwiftParameter parameterTwo = new SwiftParameter("parameterTwo", new SwiftType("String"));
    SwiftMethod method = new SwiftMethod("myMethod", Arrays.asList(parameterOne, parameterTwo));
    method.cBaseName = "ExampleClass_myMethod";
    swiftClass.methods.add(method);

    TemplateComparator.expect(
            "public protocol ExampleClass : AnyObject {\n"
                + "    func myMethod(parameterOne: Int, parameterTwo: String) -> Void\n"
                + "}\n")
        .expect(
            "internal class _ExampleClass: ExampleClass {\n"
                + "    public func myMethod(parameterOne: Int, parameterTwo: String) -> Void {\n"
                + "        return ExampleClass_myMethod(c_instance, parameterOne, parameterTwo)\n"
                + "    }\n"
                + "}\n")
        .build()
        .assertMatches(generateFromClass(swiftClass));
  }

  @Test
  public void methodWithArrayParameter() {
    SwiftClass swiftClass = SwiftClass.builder("MyClass").isInterface(true).build();
    swiftClass.implementsProtocols.add("MyClass");
    SwiftMethod method =
        new SwiftMethod(
            "myMethod",
            Collections.singletonList(new SwiftParameter("array", new SwiftType("[UInt8]"))));
    method.cBaseName = "MyClass_myMethod";
    swiftClass.methods.add(method);

    TemplateComparator.expect(
            "public protocol MyClass : AnyObject {\n"
                + "    func myMethod(array: [UInt8]) -> Void\n"
                + "}\n")
        .expect(
            "internal class _MyClass: MyClass {\n"
                + "    public func myMethod(array: [UInt8]) -> Void {\n"
                + "        return MyClass_myMethod(c_instance, array)\n"
                + "    }\n"
                + "}\n")
        .build()
        .assertMatches(generateFromClass(swiftClass));
  }

  @Test
  public void methodWithComment() {
    SwiftClass swiftClass = SwiftClass.builder("CommentedExampleClass").isInterface(true).build();
    swiftClass.implementsProtocols.add("CommentedExampleClass");
    SwiftMethod method =
        new SwiftMethod(
            "myMethod",
            Collections.singletonList(new SwiftParameter("myParameter", new SwiftType("String"))));

    method.returnType = new SwiftType("Int");
    method.comment = "Do something";
    method.cBaseName = "CommentedExampleClass_myMethod";

    swiftClass.methods.add(method);

    TemplateComparator.expect(
            "public protocol CommentedExampleClass : AnyObject {\n"
                + "    /**\n"
                + "     * Do something\n"
                + "     */\n"
                + "    func myMethod(myParameter: String) -> Int\n"
                + "}\n")
        .expect(
            "internal class _CommentedExampleClass: CommentedExampleClass {\n"
                + "    /**\n"
                + "     * Do something\n"
                + "     */\n"
                + "    public func myMethod(myParameter: String) -> Int {\n"
                + "        return CommentedExampleClass_myMethod(c_instance, myParameter)\n"
                + "    }\n"
                + "}\n")
        .build()
        .assertMatches(generateFromClass(swiftClass));
  }

  @Test
  public void staticMethod() {
    SwiftClass swiftClass = SwiftClass.builder("MyClass").build();
    SwiftMethod method = new SwiftMethod("myStaticMethod");
    method.isStatic = true;
    method.cBaseName = "MyClass_myStaticMethod";
    swiftClass.methods.add(method);

    final String expected =
        "import Foundation\n"
            + "public class MyClass {\n"
            + "    public static func myStaticMethod() -> Void {\n"
            + "        return MyClass_myStaticMethod()\n"
            + "    }\n"
            + "}\n";
    final String generated = generateFromClass(swiftClass);
    TemplateComparison.assertEqualContent(expected, generated);
  }

  @Test
  public void helloWorldGeneration() {
    SwiftClass swiftClass = SwiftClass.builder("HelloWorld").build();
    SwiftMethod method =
        new SwiftMethod(
            "helloWorldMethod",
            Collections.singletonList(new SwiftParameter("inputString", SwiftType.STRING)));
    method.returnType = new SwiftType("String", TypeCategory.BUILTIN_STRING).createOptionalType();
    method.isStatic = true;
    method.cBaseName = "HelloWorld_helloWorldMethod";
    swiftClass.methods.add(method);
    final String expected =
        "import Foundation\n"
            + "public class HelloWorld {\n"
            + "    public static func helloWorldMethod(inputString: String) -> String? {\n"
            + "        let result_string_handle = HelloWorld_helloWorldMethod(inputString)\n"
            + "        defer {\n"
            + "            std_string_release(result_string_handle)\n"
            + "        }\n"
            + "        return String(data: Data(bytes: std_string_data_get(result_string_handle),\n"
            + "                                 count: Int(std_string_size_get(result_string_handle))), encoding: .utf8)\n"
            + "    }\n"
            + "}\n";
    final String generated = generateFromClass(swiftClass);
    TemplateComparison.assertEqualContent(expected, generated);
  }

  @Test
  public void methodWithByteBufferInput() {
    SwiftClass swiftClass = SwiftClass.builder("HelloWorld").build();
    SwiftParameter swiftParameter = new SwiftParameter("byteBuffer", SwiftType.DATA);
    SwiftMethod method =
        new SwiftMethod("testBuffer", new ArrayList<>(Arrays.asList(swiftParameter)));
    method.isStatic = true;
    method.cBaseName = "HelloWorld_testBuffer";
    final String expected =
        "import Foundation\n"
            + "public class HelloWorld {\n"
            + "    public static func testBuffer(byteBuffer: Data) -> Void {\n"
            + "        return byteBuffer.withUnsafeBytes { (byteBuffer_ptr: UnsafePointer<UInt8>) -> Void in\n"
            + "            return HelloWorld_testBuffer(byteBuffer_ptr, Int64(byteBuffer.count))\n"
            + "        }\n"
            + "    }\n"
            + "}\n";
    swiftClass.methods.add(method);
    final String generated = generateFromClass(swiftClass);
    TemplateComparison.assertEqualContent(expected, generated);
  }

  @Test
  public void methodWithByteBufferInputAndDifferentVariableName() {
    SwiftClass swiftClass = SwiftClass.builder("HelloWorld").build();
    SwiftParameter swiftParameter = new SwiftParameter("data", SwiftType.DATA, "byteBuffer");
    SwiftMethod method =
        new SwiftMethod("testBuffer", new ArrayList<>(Arrays.asList(swiftParameter)));
    method.isStatic = true;
    method.cBaseName = "HelloWorld_testBuffer";
    final String expected =
        "import Foundation\n"
            + "public class HelloWorld {\n"
            + "    public static func testBuffer(data byteBuffer: Data) -> Void {\n"
            + "        return byteBuffer.withUnsafeBytes { (byteBuffer_ptr: UnsafePointer<UInt8>) -> Void in\n"
            + "            return HelloWorld_testBuffer(byteBuffer_ptr, Int64(byteBuffer.count))\n"
            + "        }\n"
            + "    }\n"
            + "}\n";
    swiftClass.methods.add(method);
    final String generated = generateFromClass(swiftClass);
    TemplateComparison.assertEqualContent(expected, generated);
  }

  @Test
  public void methodWithMultipleByteBufferInputStringAndInteger() {
    SwiftClass swiftClass = SwiftClass.builder("HelloWorld").build();
    SwiftParameter param1 = new SwiftParameter("byteBuffer", SwiftType.DATA);
    SwiftParameter param2 = new SwiftParameter("text", SwiftType.STRING);
    SwiftParameter param3 = new SwiftParameter("number", new SwiftType("Int"));
    SwiftParameter param4 = new SwiftParameter("data2", SwiftType.DATA);
    SwiftMethod method =
        new SwiftMethod(
            "testBuffer", new ArrayList<>(Arrays.asList(param1, param2, param3, param4)));
    method.isStatic = true;
    method.cBaseName = "HelloWorld_testBuffer";
    final String expected =
        "import Foundation\n"
            + "public class HelloWorld {\n"
            + "    public static func testBuffer(byteBuffer: Data, text: String, number: Int, data2: Data) -> Void {\n"
            + "        return byteBuffer.withUnsafeBytes { (byteBuffer_ptr: UnsafePointer<UInt8>) -> Void in\n"
            + "            return data2.withUnsafeBytes { (data2_ptr: UnsafePointer<UInt8>) -> Void in\n"
            + "                return HelloWorld_testBuffer(byteBuffer_ptr, Int64(byteBuffer.count), text, number, data2_ptr, Int64(data2.count))\n"
            + "            }\n"
            + "        }\n"
            + "    }\n"
            + "}\n";
    swiftClass.methods.add(method);
    final String generated = generateFromClass(swiftClass);
    TemplateComparison.assertEqualContent(expected, generated);
  }

  @Test
  public void methodWithMultipleByteBufferInputStringAndIntegerReturningData() {
    SwiftClass swiftClass = SwiftClass.builder("HelloWorld").build();
    SwiftParameter param1 = new SwiftParameter("byteBuffer", SwiftType.DATA);
    SwiftParameter param2 = new SwiftParameter("text", SwiftType.STRING);
    SwiftParameter param3 = new SwiftParameter("number", new SwiftType("Int"));
    SwiftParameter param4 = new SwiftParameter("data2", SwiftType.DATA);
    SwiftMethod method =
        new SwiftMethod(
            "testBuffer", new ArrayList<>(Arrays.asList(param1, param2, param3, param4)));
    method.isStatic = true;
    method.returnType =
        new SwiftType("Data", SwiftType.TypeCategory.BUILTIN_BYTEBUFFER).createOptionalType();
    method.cBaseName = "HelloWorld_testBuffer";
    final String expected =
        "import Foundation\n"
            + "public class HelloWorld {\n"
            + "    public static func testBuffer(byteBuffer: Data, text: String, number: Int, data2: Data) -> Data? {\n"
            + "        return byteBuffer.withUnsafeBytes { (byteBuffer_ptr: UnsafePointer<UInt8>) -> Data? in\n"
            + "            return data2.withUnsafeBytes { (data2_ptr: UnsafePointer<UInt8>) -> Data? in\n"
            + "                let result_data_handle = HelloWorld_testBuffer(byteBuffer_ptr, Int64(byteBuffer.count), text, number, data2_ptr, Int64(data2.count))\n"
            + "                defer {\n"
            + "                    byteArray_release(result_data_handle)\n"
            + "                }\n"
            + "                return Data(bytes: byteArray_data_get(result_data_handle), count: Int(byteArray_size_get(result_data_handle)))\n"
            + "            }\n"
            + "        }\n"
            + "    }\n"
            + "}\n";
    swiftClass.methods.add(method);
    final String generated = generateFromClass(swiftClass);
    TemplateComparison.assertEqualContent(expected, generated);
  }

  @Test
  public void methodWithMultipleByteBufferInputStringAndIntegerReturningInt() {
    SwiftClass swiftClass = SwiftClass.builder("HelloWorld").build();
    SwiftParameter param1 = new SwiftParameter("byteBuffer", SwiftType.DATA);
    SwiftParameter param2 = new SwiftParameter("text", SwiftType.STRING);
    SwiftParameter param3 = new SwiftParameter("number", new SwiftType("Int"));
    SwiftParameter param4 = new SwiftParameter("data2", SwiftType.DATA);
    SwiftMethod method =
        new SwiftMethod(
            "testBuffer", new ArrayList<>(Arrays.asList(param1, param2, param3, param4)));
    method.isStatic = true;
    method.returnType = new SwiftType("Int");
    method.cBaseName = "HelloWorld_testBuffer";
    final String expected =
        "import Foundation\n"
            + "public class HelloWorld {\n"
            + "    public static func testBuffer(byteBuffer: Data, text: String, number: Int, data2: Data) -> Int {\n"
            + "        return byteBuffer.withUnsafeBytes { (byteBuffer_ptr: UnsafePointer<UInt8>) -> Int in\n"
            + "            return data2.withUnsafeBytes { (data2_ptr: UnsafePointer<UInt8>) -> Int in\n"
            + "                return HelloWorld_testBuffer(byteBuffer_ptr, Int64(byteBuffer.count), text, number, data2_ptr, Int64(data2.count))\n"
            + "            }\n"
            + "        }\n"
            + "    }\n"
            + "}\n";
    swiftClass.methods.add(method);
    final String generated = generateFromClass(swiftClass);
    TemplateComparison.assertEqualContent(expected, generated);
  }

  @Test
  public void staticMethodTakingStruct() {
    SwiftClass swiftClass = SwiftClass.builder("HelloWorld").build();
    SwiftContainerType swiftStruct =
        SwiftContainerType.builder("SomeStruct")
            .cPrefix(swiftClass.name + "_" + "SomeStruct")
            .build();
    SwiftMethod method =
        new SwiftMethod(
            "methodTakingStruct", singletonList(new SwiftParameter("inputParam", swiftStruct)));
    method.isStatic = true;
    method.cBaseName = "HelloWorld_methodTakingStruct";
    swiftClass.methods.add(method);
    final String expected =
        "import Foundation\n"
            + "public class HelloWorld {\n"
            + "    public static func methodTakingStruct(inputParam: SomeStruct) -> Void {\n"
            + "        let inputParamHandle = inputParam.convertToCType()\n"
            + "        defer {\n"
            + "            HelloWorld_SomeStruct_release(inputParamHandle)\n"
            + "        }\n"
            + "        return HelloWorld_methodTakingStruct(inputParamHandle)\n"
            + "    }\n"
            + "}\n";
    String generated = generateFromClass(swiftClass);
    TemplateComparison.assertEqualContent(expected, generated);
  }

  @Test
  public void staticMethodReturningStruct() {
    SwiftClass swiftClass = SwiftClass.builder("HelloWorld").build();
    SwiftContainerType swiftStruct =
        SwiftContainerType.builder("SomeStruct")
            .cPrefix(swiftClass.name + "_" + "SomeStruct")
            .build();
    SwiftMethod method = new SwiftMethod("methodReturningStruct");
    method.isStatic = true;
    method.returnType = swiftStruct.createOptionalType();
    method.cBaseName = "HelloWorld_methodReturningStruct";
    swiftClass.methods.add(method);
    final String expected =
        "import Foundation\n"
            + "public class HelloWorld {\n"
            + "    public static func methodReturningStruct() -> SomeStruct? {\n"
            + "        let cResult = HelloWorld_methodReturningStruct()\n"
            + "        defer {\n"
            + "            HelloWorld_SomeStruct_release(cResult)\n"
            + "        }\n"
            + "        return SomeStruct(cSomeStruct: cResult)\n"
            + "    }\n"
            + "}\n";
    String generated = generateFromClass(swiftClass);
    TemplateComparison.assertEqualContent(expected, generated);
  }

  @Test
  public void staticMethodTakingMultipleParamsAndReturningStruct() {
    SwiftClass swiftClass = SwiftClass.builder("HelloWorld").build();
    SwiftContainerType inputStruct =
        SwiftContainerType.builder("GeoLocation")
            .cPrefix(swiftClass.name + "_" + "GeoLocation")
            .build();
    SwiftMethod method =
        new SwiftMethod(
            "fancyMethod",
            Stream.of(
                    new SwiftParameter("icon", SwiftType.DATA),
                    new SwiftParameter("name", SwiftType.STRING),
                    new SwiftParameter("location", inputStruct))
                .collect(toList()));
    method.isStatic = true;
    SwiftContainerType outputStruct =
        SwiftContainerType.builder("SomeStruct")
            .cPrefix(swiftClass.name + "_" + "SomeStruct")
            .build();
    method.returnType = outputStruct.createOptionalType();
    method.cBaseName = "HelloWorld_fancyMethod";
    swiftClass.methods.add(method);

    final String expected =
        "import Foundation\n"
            + "public class HelloWorld {\n"
            + "    public static func fancyMethod(icon: Data, name: String, location: GeoLocation) -> SomeStruct? {\n"
            + "        return icon.withUnsafeBytes { (icon_ptr: UnsafePointer<UInt8>) -> SomeStruct? in\n"
            + "            let locationHandle = location.convertToCType()\n"
            + "            defer {\n"
            + "                HelloWorld_GeoLocation_release(locationHandle)\n"
            + "            }\n"
            + "            let cResult = HelloWorld_fancyMethod(icon_ptr, Int64(icon.count), name, locationHandle)\n"
            + "            defer {\n"
            + "                HelloWorld_SomeStruct_release(cResult)\n"
            + "            }\n"
            + "            return SomeStruct(cSomeStruct: cResult)\n"
            + "        }\n"
            + "    }\n"
            + "}\n";

    String generated = generateFromClass(swiftClass);
    TemplateComparison.assertEqualContent(expected, generated);
  }

  @Test
  public void interfaceWithTwoStructsAndMethod() {
    SwiftFile file = new SwiftFile();
    SwiftClass swiftClass = SwiftClass.builder("SomeClass").isInterface(true).build();
    SwiftContainerType firstStruct =
        SwiftContainerType.builder("FirstStruct").cPrefix("CPrefix").cType("CType").build();
    SwiftContainerType secondStruct =
        SwiftContainerType.builder("SecondStruct").cPrefix("CPrefix").cType("CType").build();
    swiftClass.methods.add(new SwiftMethod("SomeMethod"));
    swiftClass.implementsProtocols.add(swiftClass.name);
    file.structs.add(firstStruct);
    file.structs.add(secondStruct);
    file.classes.add(swiftClass);

    TemplateComparator expected =
        TemplateComparator.expect(
                "public protocol SomeClass : AnyObject {\n"
                    + "    func SomeMethod() -> Void\n"
                    + "}\n")
            .expect(
                "internal class _SomeClass: SomeClass {\n"
                    + "    public func SomeMethod() -> Void {\n"
                    + "        return (c_instance)\n"
                    + "    }\n"
                    + "}\n")
            .expect(
                "public struct FirstStruct {\n"
                    + "    public init() {\n"
                    + "    }\n"
                    + "    internal init?(cFirstStruct: CType) {\n"
                    + "    }\n"
                    + "    internal func convertToCType() -> CType {\n"
                    + "        let result = CPrefix_create()\n"
                    + "        fillFunction(result)\n"
                    + "        return result\n"
                    + "    }\n"
                    + "    internal func fillFunction(_ cFirstStruct: CType) -> Void {\n"
                    + "    }\n"
                    + "}\n")
            .expect(
                "public struct SecondStruct {\n"
                    + "    public init() {\n"
                    + "    }\n"
                    + "    internal init?(cSecondStruct: CType) {\n"
                    + "    }\n"
                    + "    internal func convertToCType() -> CType {\n"
                    + "        let result = CPrefix_create()\n"
                    + "        fillFunction(result)\n"
                    + "        return result\n"
                    + "    }\n"
                    + "    internal func fillFunction(_ cSecondStruct: CType) -> Void {\n"
                    + "    }\n"
                    + "}\n")
            .build();

    final String generated = generate(file);

    expected.assertMatches(generated);
  }

  @Test
  public void typeCollectionWithStruct() {
    SwiftContainerType firstSturct =
        SwiftContainerType.builder("FirstStruct").cPrefix("CPrefix").cType("CType").build();
    SwiftFile file = new SwiftFile();
    file.structs.add(firstSturct);

    final String expected =
        "import Foundation\n"
            + "public struct FirstStruct {\n"
            + "    public init() {\n"
            + "    }\n"
            + "    internal init?(cFirstStruct: CType) {\n"
            + "    }\n"
            + "    internal func convertToCType() -> CType {\n"
            + "        let result = CPrefix_create()\n"
            + "        fillFunction(result)\n"
            + "        return result\n"
            + "    }\n"
            + "    internal func fillFunction(_ cFirstStruct: CType) -> Void {\n"
            + "    }\n"
            + "}\n";
    final String generated = generate(file);
    TemplateComparison.assertEqualContent(expected, generated);
  }

  @Test
  public void typeCollectionWithEnum() {
    SwiftEnum swiftEnum =
        SwiftEnum.builder("EnumSwiftName").comment("Some comment on enum type").build();
    SwiftFile file = new SwiftFile();
    file.enums.add(swiftEnum);
    final String expected =
        "import Foundation\n"
            + "/**\n"
            + " * Some comment on enum type\n"
            + " */\n"
            + "public enum EnumSwiftName : UInt32 {\n"
            + "}\n";

    final String generated = generate(file);

    TemplateComparison.assertEqualContent(expected, generated);
  }

  @Test
  public void renderEnumWithItemWithCommentAndValue() {
    SwiftEnumItem enumItem =
        SwiftEnumItem.builder("ItemName")
            .comment("Some comment on enumerator")
            .value(new SwiftValue("VALUE"))
            .build();
    SwiftEnum swiftEnum =
        SwiftEnum.builder("EnumSwiftName")
            .comment("Some comment on enum type")
            .items(singletonList(enumItem))
            .build();
    SwiftFile file = new SwiftFile();
    file.enums.add(swiftEnum);
    final String expected =
        "import Foundation\n"
            + "/**\n"
            + " * Some comment on enum type\n"
            + " */\n"
            + "public enum EnumSwiftName : UInt32 {\n"
            + "    /**\n"
            + "     * Some comment on enumerator\n"
            + "     */\n"
            + "    case ItemName = VALUE\n"
            + "}\n";

    final String generated = generate(file);

    TemplateComparison.assertEqualContent(expected, generated);
  }

  @Test
  public void interfaceWithEnum() {
    SwiftFile swiftFile = new SwiftFile();
    swiftFile.enums.add(
        SwiftEnum.builder("EnumSwiftName").comment("Some comment on enum type").build());
    SwiftClass swiftClass = SwiftClass.builder("TestInterface").isInterface(true).build();
    swiftFile.classes.add(swiftClass);

    TemplateComparator expected =
        TemplateComparator.expect("public protocol TestInterface : AnyObject {\n" + "}\n")
            .expect("internal class _TestInterface {\n" + "}\n")
            .expect(
                "/**\n"
                    + " * Some comment on enum type\n"
                    + " */\n"
                    + "public enum EnumSwiftName : UInt32 {\n"
                    + "}\n")
            .build();

    final String generated = generate(swiftFile);

    expected.assertMatches(generated);
  }

  @Test
  public void classWithInternalConstructor() {
    SwiftClass swiftClass =
        SwiftClass.builder("HelloWorld")
            .cInstanceRef("HelloWorldRef")
            .cInstance("HellowWorld")
            .isInterface(true)
            .build();
    swiftClass.implementsProtocols.add("HelloWorld");
    SwiftMethod method = new SwiftMethod("instanceMethod");
    method.returnType = new SwiftType("Int");
    method.cBaseName = "HelloWorld_instanceMethod";
    swiftClass.methods.add(method);

    TemplateComparator expected =
        TemplateComparator.expect(
                "public protocol HelloWorld : AnyObject {\n"
                    + "    func instanceMethod() -> Int\n"
                    + "}\n")
            .expect(
                "internal class _HelloWorld: HelloWorld {\n"
                    + "    let c_instance : HelloWorldRef\n"
                    + "    required init?(cHelloWorld: HelloWorldRef) {\n"
                    + "        c_instance = cHelloWorld\n"
                    + "    }\n"
                    + "    deinit {\n"
                    + "        HellowWorld_release(c_instance)\n"
                    + "    }\n"
                    + "    public func instanceMethod() -> Int {\n"
                    + "        return HelloWorld_instanceMethod(c_instance)\n"
                    + "    }\n"
                    + "}\n")
            .build();

    final String generated = generateFromClass(swiftClass);

    expected.assertMatches(generated);
  }

  @Test
  public void factoryClassCallingPrivateConstructor() {
    SwiftClass swiftClass =
        SwiftClass.builder("HellowWorldFactory")
            .cInstanceRef("HellowWorldFactoryRef")
            .cInstance("HellowWorldFactory")
            .build();

    SwiftMethod method = new SwiftMethod("createInstanceMethod");
    SwiftContainerType mappedType =
        SwiftContainerType.builder("HelloWorld")
            .category(TypeCategory.CLASS)
            .implementingClass("_HelloWorld")
            .build();
    method.returnType = mappedType;
    method.cBaseName = "HelloWorld_createInstanceMethod";
    method.isStatic = true;
    swiftClass.methods.add(method);

    TemplateComparator expected =
        TemplateComparator.expect(
                "public class HellowWorldFactory {\n"
                    + "    let c_instance : HellowWorldFactoryRef\n"
                    + "    public required init?(cHellowWorldFactory: HellowWorldFactoryRef) {\n"
                    + "        c_instance = cHellowWorldFactory\n"
                    + "    }\n"
                    + "    deinit {\n"
                    + "        HellowWorldFactory_release(c_instance)\n"
                    + "    }\n"
                    + "    public static func createInstanceMethod() -> HelloWorld {\n"
                    + "        let cResult = HelloWorld_createInstanceMethod()\n"
                    + "        return _HelloWorld(cHelloWorld: cResult)\n"
                    + "    }\n"
                    + "}")
            .build();

    final String generated = generateFromClass(swiftClass);
    expected.assertMatches(generated);
  }

  @Test
  public void typedefGenerationInProtocol() {
    SwiftClass swiftClass = SwiftClass.builder("HellowWorldFactory").isInterface(true).build();
    SwiftTypeDef typedef = new SwiftTypeDef("MyTypeDef", new SwiftType("Int"));
    swiftClass.typedefs.add(typedef);

    TemplateComparator.expect(
            "public protocol HellowWorldFactory : AnyObject {\n"
                + "    typealias MyTypeDef = Int\n"
                + "}\n")
        .expect("internal class _HellowWorldFactory {\n" + "}\n")
        .build()
        .assertMatches(generateFromClass(swiftClass));
  }

  @Test
  public void typedeGenerationInClass() {
    SwiftClass swiftClass = SwiftClass.builder("HellowWorldFactory").build();
    SwiftTypeDef typedef = new SwiftTypeDef("MyTypeDef", new SwiftType("Int"));
    swiftClass.typedefs.add(typedef);
    SwiftMethod method = new SwiftMethod("createInstanceMethod");
    SwiftContainerType mappedType =
        SwiftContainerType.builder("HelloWorld")
            .category(TypeCategory.CLASS)
            .implementingClass("_HelloWorld")
            .build();
    method.returnType = mappedType;
    method.cBaseName = "HelloWorld_createInstanceMethod";
    method.isStatic = true;
    swiftClass.methods.add(method);

    TemplateComparator.expect(
            "public class HellowWorldFactory {\n"
                + "    public typealias MyTypeDef = Int\n"
                + "    public static func createInstanceMethod() -> HelloWorld {\n"
                + "        let cResult = HelloWorld_createInstanceMethod()\n"
                + "        return _HelloWorld(cHelloWorld: cResult)\n"
                + "    }\n"
                + "}\n")
        .build()
        .assertMatches(generateFromClass(swiftClass));
  }

  @Test
  public void nestedTypedeGenerationInClass() {

    SwiftClass swiftClass = SwiftClass.builder("HellowWorldFactory").build();
    SwiftTypeDef typedef =
        new SwiftTypeDef(
            "HellowWorldFactory.MyNestedTypeDef", new SwiftType("HellowWorldFactory.MyTypeDef"));
    SwiftTypeDef typedef2 = new SwiftTypeDef("HellowWorldFactory.MyTypeDef", new SwiftType("Int"));
    swiftClass.typedefs.add(typedef);
    swiftClass.typedefs.add(typedef2);

    SwiftMethod method = new SwiftMethod("createInstanceMethod");
    SwiftContainerType mappedType =
        SwiftContainerType.builder("HelloWorld").implementingClass("_HelloWorld").build();
    method.returnType = typedef.type;
    method.cBaseName = "HelloWorld_createInstanceMethod";
    method.isStatic = true;
    swiftClass.methods.add(method);

    final String expected =
        "import Foundation\n"
            + "public class HellowWorldFactory {\n"
            + "    public typealias MyNestedTypeDef = HellowWorldFactory.MyTypeDef\n"
            + "    public typealias MyTypeDef = Int\n"
            + "    public static func createInstanceMethod() -> HellowWorldFactory.MyTypeDef {\n"
            + "        return HelloWorld_createInstanceMethod()\n"
            + "    }\n"
            + "}\n";

    final String generated = generateFromClass(swiftClass);
    TemplateComparison.assertEqualContent(expected, generated);
  }

  @Test
  public void protocolWithPropertyOfDataType() {
    SwiftClass swiftClass =
        SwiftClass.builder("SomeClassWithProperty")
            .cInstanceRef("SomeClassWithPropertyRef")
            .isInterface(true)
            .build();
    SwiftProperty someProperty =
        new SwiftProperty("someAttributeName", SwiftType.DATA, false, "CBRIDGE_DELEGATE");
    swiftClass.properties.add(someProperty);
    TemplateComparator expected =
        TemplateComparator.expect(
                "public protocol SomeClassWithProperty : AnyObject {\n"
                    + "    var someAttributeName: Data { get set }\n"
                    + "}\n")
            .expect(
                "internal class _SomeClassWithProperty {\n"
                    + "    var someAttributeName: Data {\n"
                    + "        get {\n"
                    + "            let result_data_handle = CBRIDGE_DELEGATE_get(c_instance)\n"
                    + "            defer {\n"
                    + "                byteArray_release(result_data_handle)\n"
                    + "            }\n"
                    + "            return Data(bytes: byteArray_data_get(result_data_handle), count: Int(byteArray_size_get(result_data_handle)))\n"
                    + "        }\n"
                    + "        set {\n"
                    + "            return newValue.withUnsafeBytes { (newValue_ptr: UnsafePointer<UInt8>) -> Void in\n"
                    + "                return CBRIDGE_DELEGATE_set(c_instance, newValue_ptr, Int64(newValue.count))\n"
                    + "            }\n"
                    + "        }\n"
                    + "    }\n"
                    + "    let c_instance : SomeClassWithPropertyRef\n"
                    + "    required init?(cSomeClassWithProperty: SomeClassWithPropertyRef) {\n"
                    + "        c_instance = cSomeClassWithProperty\n"
                    + "    }\n"
                    + "    deinit {\n"
                    + "        _release(c_instance)\n"
                    + "    }\n"
                    + "}\n")
            .build();

    final String generated = generateFromClass(swiftClass);
    expected.assertMatches(generated);
  }

  @Test
  public void classWithPropertyOfDataType() {
    SwiftClass swiftClass =
        SwiftClass.builder("SomeClassWithProperty")
            .cInstanceRef("SomeClassWithPropertyRef")
            .build();
    SwiftProperty someProperty =
        new SwiftProperty("someAttributeName", SwiftType.DATA, false, "CBRIDGE_DELEGATE");
    swiftClass.properties.add(someProperty);
    final String expected =
        "import Foundation\n"
            + "internal func getRef(_ ref: SomeClassWithProperty) -> "
            + "RefHolder<SomeClassWithPropertyRef> {\n"
            + "    return RefHolder<SomeClassWithPropertyRef>(ref.c_instance)\n"
            + "}\n"
            + "public class SomeClassWithProperty {\n"
            + "    public var someAttributeName: Data {\n"
            + "        get {\n"
            + "            let result_data_handle = CBRIDGE_DELEGATE_get(c_instance)\n"
            + "            defer {\n"
            + "                byteArray_release(result_data_handle)\n"
            + "            }\n"
            + "            return Data(bytes: byteArray_data_get(result_data_handle), count: Int"
            + "(byteArray_size_get(result_data_handle)))\n"
            + "        }\n"
            + "        set {\n"
            + "            return newValue.withUnsafeBytes { (newValue_ptr: UnsafePointer<UInt8>) -> "
            + "Void in\n"
            + "                return CBRIDGE_DELEGATE_set(c_instance, newValue_ptr, Int64(newValue"
            + ".count))\n"
            + "            }\n"
            + "        }\n"
            + "    }\n"
            + "    let c_instance : SomeClassWithPropertyRef\n"
            + "    public required init?(cSomeClassWithProperty: SomeClassWithPropertyRef) {\n"
            + "        c_instance = cSomeClassWithProperty\n"
            + "    }\n"
            + "    deinit {\n"
            + "        _release(c_instance)\n"
            + "    }\n"
            + "}\n";

    final String generated = generateFromClass(swiftClass);
    TemplateComparison.assertEqualContent(expected, generated);
  }

  @Test
  public void classWithReadonlyProperties() {
    SwiftClass swiftClass =
        SwiftClass.builder("SomeClassWithProperty")
            .cInstanceRef("SomeClassWithPropertyRef")
            .isInterface(true)
            .build();
    swiftClass.properties.add(
        new SwiftProperty(
            "someStringAttribute", SwiftType.STRING, true, "CBRIDGE_DELEGATE_FOR_STRING"));
    SwiftContainerType swiftStruct =
        SwiftContainerType.builder("SomeStructType").cPrefix("SomeStructType").build();
    swiftClass.properties.add(
        new SwiftProperty("someStructAttribute", swiftStruct, true, "CBRIDGE_DELEGATE_FOR_STRUCT"));
    swiftClass.properties.add(
        new SwiftProperty(
            "someEnumAttribute",
            new SwiftType("SomeEnumType", TypeCategory.ENUM),
            true,
            "CBRIDGE_DELEGATE_FOR_ENUM"));
    TemplateComparator expected =
        TemplateComparator.expect(
                "public protocol SomeClassWithProperty : AnyObject {\n"
                    + "    var someStringAttribute: String { get }\n"
                    + "    var someStructAttribute: SomeStructType { get }\n"
                    + "    var someEnumAttribute: SomeEnumType { get }\n"
                    + "}\n")
            .expect(
                "internal class _SomeClassWithProperty {\n"
                    + "    var someStringAttribute: String {\n"
                    + "        get {\n"
                    + "            let result_string_handle = CBRIDGE_DELEGATE_FOR_STRING_get(c_instance)\n"
                    + "            defer {\n"
                    + "                std_string_release(result_string_handle)\n"
                    + "            }\n"
                    + "            return String(data: Data(bytes: std_string_data_get(result_string_handle),\n"
                    + "                                     count: Int(std_string_size_get(result_string_handle))), encoding: .utf8)!\n"
                    + "        }\n"
                    + "    }\n"
                    + "    var someStructAttribute: SomeStructType {\n"
                    + "        get {\n"
                    + "            let cResult = CBRIDGE_DELEGATE_FOR_STRUCT_get(c_instance)\n"
                    + "            defer {\n"
                    + "                SomeStructType_release(cResult)\n"
                    + "            }\n"
                    + "            return SomeStructType(cSomeStructType: cResult)!\n"
                    + "        }\n"
                    + "    }\n"
                    + "    var someEnumAttribute: SomeEnumType {\n"
                    + "        get {\n"
                    + "            let cResult = CBRIDGE_DELEGATE_FOR_ENUM_get(c_instance)\n"
                    + "            return SomeEnumType(rawValue: cResult)!\n"
                    + "        }\n"
                    + "    }\n"
                    + "    let c_instance : SomeClassWithPropertyRef\n"
                    + "    required init?(cSomeClassWithProperty: SomeClassWithPropertyRef) {\n"
                    + "        c_instance = cSomeClassWithProperty\n"
                    + "    }\n"
                    + "    deinit {\n"
                    + "        _release(c_instance)\n"
                    + "    }\n"
                    + "}\n")
            .build();

    final String generated = generateFromClass(swiftClass);
    expected.assertMatches(generated);
  }

  public void classWithBase() {
    SwiftClass swiftClass = SwiftClass.builder("TestClass").parentClass("SuperClass").build();
    final String expected =
        "import Foundation\n"
            + "internal func getRef(_ ref: TestClass) -> RefHolder<> {\n"
            + "    guard let instanceReference = ref as? _TestClass else {\n"
            + "        fatalError(\"Not implemented yet\")\n"
            + "    }\n"
            + "    return RefHolder<>(instanceReference.c_instance)\n"
            + "}\n"
            + "public protocol TestClass {\n"
            + "}\n"
            + "internal class _TestClass: SuperClass {\n"
            + "}\n";
    final String generated = generateFromClass(swiftClass);
    TemplateComparison.assertEqualContent(expected, generated);
  }

  @Test
  public void classWithProtocol() {
    SwiftClass swiftClass =
        SwiftClass.builder("TestClass")
            .isInterface(true)
            .cInstance("CTest")
            .cInstanceRef("CTestPointerWrapper")
            .functionTableName("TestClassFunctionTable")
            .build();
    swiftClass.implementsProtocols.add("FirstProtocol");

    TemplateComparator expected =
        TemplateComparator.expect(
                "internal func getRef(_ ref: TestClass) -> RefHolder<CTestPointerWrapper> {\n"
                    + "    if let instanceReference = ref as? _TestClass {\n"
                    + "        return RefHolder<CTestPointerWrapper>(instanceReference.c_instance)\n"
                    + "    }\n"
                    + "    var functions = TestClassFunctionTable()\n"
                    + "    functions.swift_pointer = Unmanaged<AnyObject>.passRetained(ref).toOpaque()\n"
                    + "    functions.release = {swiftClass_pointer in\n"
                    + "        if let swiftClass = swiftClass_pointer {\n"
                    + "            Unmanaged<AnyObject>.fromOpaque(swiftClass).release()\n"
                    + "        }\n"
                    + "    }\n"
                    + "    return RefHolder(ref: CTest_createProxy(functions), release: CTest_release)\n"
                    + "}")
            .expect("public protocol TestClass : AnyObject {\n" + "}\n")
            .expect(
                "internal class _TestClass: FirstProtocol {\n"
                    + "    let c_instance : CTestPointerWrapper\n"
                    + "    required init?(cTestClass: CTestPointerWrapper) {\n"
                    + "        c_instance = cTestClass\n"
                    + "    }\n"
                    + "    deinit {\n"
                    + "        CTest_release(c_instance)\n"
                    + "    }\n"
                    + "}")
            .build();

    final String generated = generateFromClass(swiftClass);
    expected.assertMatches(generated);
  }

  public void classWithBaseAndProtocols() {
    SwiftClass swiftClass = SwiftClass.builder("TestClass").parentClass("SuperClass").build();
    swiftClass.implementsProtocols.add("FirstProtocol");
    swiftClass.implementsProtocols.add("SecondProtocol");
    final String expected =
        "import Foundation\n"
            + "internal func getRef(_ ref: TestClass) -> RefHolder<> {\n"
            + "    guard let instanceReference = ref as? _TestClass else {\n"
            + "        fatalError(\"Not implemented yet\")\n"
            + "    }\n"
            + "    return RefHolder<>(instanceReference.c_instance)\n"
            + "}\n"
            + "public protocol TestClass {\n"
            + "}\n"
            + "internal class _TestClass: SuperClass, FirstProtocol, SecondProtocol {\n"
            + "}\n";
    final String generated = generateFromClass(swiftClass);
    TemplateComparison.assertEqualContent(expected, generated);
  }

  @Test
  public void classWithoutProtocolWithStructTypedefAndMethod() {
    SwiftClass swiftClass = SwiftClass.builder("SomeClass").build();
    SwiftContainerType firstStruct =
        SwiftContainerType.builder("SomeClass.FirstStruct")
            .cPrefix("CPrefix")
            .cType("CType")
            .build();
    SwiftContainerType secondStruct =
        SwiftContainerType.builder("SomeClass.SecondStruct")
            .cPrefix("CPrefix")
            .cType("CType")
            .build();
    swiftClass.typedefs.add(new SwiftTypeDef("SomeClass.RenamedStruct", secondStruct));
    SwiftMethod method =
        new SwiftMethod("SomeMethod", singletonList(new SwiftParameter("input", firstStruct)));
    method.returnType = secondStruct.createAlias("SomeClass.RenamedStruct");
    method.cBaseName = "HelloWorld_someMethod";
    method.isStatic = true;
    swiftClass.methods.add(method);
    swiftClass.structs.add(firstStruct);
    swiftClass.structs.add(secondStruct);
    final String expected =
        "import Foundation\n"
            + "public class SomeClass {\n"
            + "    public typealias RenamedStruct = SomeClass.SecondStruct\n"
            + "    public struct FirstStruct {\n"
            + "        public init() {\n"
            + "        }\n"
            + "        internal init?(cFirstStruct: CType) {\n"
            + "        }\n"
            + "        internal func convertToCType() -> CType {\n"
            + "            let result = CPrefix_create()\n"
            + "            fillFunction(result)\n"
            + "            return result\n"
            + "        }\n"
            + "        internal func fillFunction(_ cFirstStruct: CType) -> Void {\n"
            + "        }\n"
            + "    }\n"
            + "    public struct SecondStruct {\n"
            + "        public init() {\n"
            + "        }\n"
            + "        internal init?(cSecondStruct: CType) {\n"
            + "        }\n"
            + "        internal func convertToCType() -> CType {\n"
            + "            let result = CPrefix_create()\n"
            + "            fillFunction(result)\n"
            + "            return result\n"
            + "        }\n"
            + "        internal func fillFunction(_ cSecondStruct: CType) -> Void {\n"
            + "        }\n"
            + "    }\n"
            + "    public static func SomeMethod(input: SomeClass.FirstStruct) -> SomeClass.RenamedStruct {\n"
            + "        let inputHandle = input.convertToCType()\n"
            + "        defer {\n"
            + "            CPrefix_release(inputHandle)\n"
            + "        }\n"
            + "        let cResult = HelloWorld_someMethod(inputHandle)\n"
            + "        defer {\n"
            + "            CPrefix_release(cResult)\n"
            + "        }\n"
            + "        return SomeClass.SecondStruct(cSecondStruct: cResult)\n"
            + "    }\n"
            + "}\n";
    final String generated = generateFromClass(swiftClass);
    TemplateComparison.assertEqualContent(expected, generated);
  }
}
