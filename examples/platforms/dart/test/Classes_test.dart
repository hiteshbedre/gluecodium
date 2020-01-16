// -------------------------------------------------------------------------------------------------
// Copyright (C) 2016-2020 HERE Europe B.V.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
// SPDX-License-Identifier: Apache-2.0
// License-Filename: LICENSE
//
// -------------------------------------------------------------------------------------------------

import "package:test/test.dart";
import "package:hello/hello.dart";
import "../test_suite.dart";

final _testSuite = TestSuite("Classes");

void main() {
  _testSuite.test("Set same type instances", () {
    final input1 = InstancesFactory.createSimpleInstantiableOne();
    input1.setStringValue("one");
    final input2 = InstancesFactory.createSimpleInstantiableOne();
    input2.setStringValue("two");
    final nested = InstancesFactory.createNestedInstantiableOne();

    nested.setSameTypeInstances(input1, input2);
    final result1 = nested.getInstanceOne();
    final result2 = nested.getInstanceTwo();

    expect(result1.getStringValue(), equals("one"));
    expect(result2.getStringValue(), equals("two"));

    input1.release();
    input2.release();
    nested.release();
    result1.release();
    result2.release();
  });
  _testSuite.test("Set same type instances, identical instances", () {
    final input = InstancesFactory.createSimpleInstantiableOne();
    input.setStringValue("one");
    final nested = InstancesFactory.createNestedInstantiableOne();

    nested.setSameTypeInstances(input, input);
    final result1 = nested.getInstanceOne();
    final result2 = nested.getInstanceTwo();

    expect(result1.getStringValue(), equals("one"));
    expect(result2.getStringValue(), equals("one"));

    input.release();
    nested.release();
    result1.release();
    result2.release();
  });
  _testSuite.test("Get null instances", () {
    final nested = InstancesFactory.createNestedInstantiableOne();

    final result1 = nested.getInstanceOne();
    final result2 = nested.getInstanceTwo();

    expect(result1, isNull);
    expect(result2, isNull);

    nested.release();
  });
  _testSuite.test("Set null instances", () {
    final nested = InstancesFactory.createNestedInstantiableOne();

    nested.setSameTypeInstances(null, null);
    final result1 = nested.getInstanceOne();
    final result2 = nested.getInstanceTwo();

    expect(result1, isNull);
    expect(result2, isNull);

    nested.release();
  });
  _testSuite.test("Set multiple type instances", () {
    final simpleOne1 = InstancesFactory.createSimpleInstantiableOne();
    final simpleOne2 = InstancesFactory.createSimpleInstantiableOne();
    final simpleTwo = InstancesFactory.createSimpleInstantiableTwo();
    final nestedOne = InstancesFactory.createNestedInstantiableOne();
    final nestedTwo = InstancesFactory.createNestedInstantiableTwo();
    simpleOne1.setStringValue("one");
    simpleTwo.setStringValue("two");
    simpleOne2.setStringValue("other");

    nestedOne.setSameTypeInstances(simpleOne1, simpleOne2);
    nestedTwo.setMultipleTypeInstances(simpleOne1, simpleTwo, nestedOne);
    final result1 = nestedTwo.getInstantiableOne();
    final result2 = nestedTwo.getInstantiableTwo();
    final result3 = nestedTwo.getNestedInstantiable();
    final result4 = result3.getInstanceOne();
    final result5 = nestedTwo.getNestedInstantiable();
    final result6 = result5.getInstanceTwo();

    expect(result1.getStringValue(), equals("one"));
    expect(result2.getStringValue(), equals("two"));
    expect(result4.getStringValue(), equals("one"));
    expect(result6.getStringValue(), equals("other"));

    simpleOne1.release();
    simpleOne2.release();
    simpleTwo.release();
    nestedOne.release();
    nestedTwo.release();
    result1.release();
    result2.release();
    result3.release();
    result4.release();
    result5.release();
    result6.release();
  });
  _testSuite.test("Set self instance", () {
    final nested = InstancesFactory.createNestedInstantiableTwo();
    final simpleOne = InstancesFactory.createSimpleInstantiableOne();
    simpleOne.setStringValue("one");
    nested.setMultipleTypeInstances(simpleOne, null, null);

    nested.setSelfInstantiable(nested);
    final result1 = nested.getSelfInstantiable();
    final result2 = result1.getInstantiableOne();

    expect(result2.getStringValue(), equals("one"));

    nested.setSelfInstantiable(null);
    nested.release();
    simpleOne.release();
    result1.release();
    result2.release();
  });
  _testSuite.test("Set self instance null", () {
    final nested = InstancesFactory.createNestedInstantiableTwo();

    nested.setSelfInstantiable(null);

    expect(nested.getSelfInstantiable(), isNull);

    nested.release();
  });
  _testSuite.test("Get instance from struct", () {
    final result = InstanceInStruct.createInStruct();

    expect(result.mySelf.getStringValue(), equals("foo"));

    result.mySelf.release();
  });
  _testSuite.test("Get null instance from struct", () {
    final result = InstanceInStruct.createNullInStruct();

    expect(result.mySelf, isNull);
  });
}