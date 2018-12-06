/*
 * Copyright (c) 2016-2018 HERE Europe B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 * License-Filename: LICENSE
 */

package com.here.genium.generator.cpp;

import com.here.genium.generator.common.NameRules;
import com.here.genium.generator.common.StringValueMapper;
import com.here.genium.model.cpp.*;
import com.here.genium.model.franca.FrancaDeploymentModel;
import org.apache.commons.text.StringEscapeUtils;
import org.franca.core.franca.*;

public class CppValueMapper {

  private final FrancaDeploymentModel deploymentModel;
  private final CppNameResolver nameResolver;

  public CppValueMapper(
      final FrancaDeploymentModel deploymentModel, final CppNameResolver nameResolver) {
    this.deploymentModel = deploymentModel;
    this.nameResolver = nameResolver;
  }

  public CppValue map(final FInitializerExpression francaExpression) {

    if (francaExpression instanceof FConstant || francaExpression instanceof FUnaryOperation) {
      String stringValue = StringValueMapper.map(francaExpression);
      if (stringValue == null) {
        return null;
      }

      return new CppValue(
          francaExpression instanceof FFloatConstant ? stringValue + "f" : stringValue);
    } else if (francaExpression instanceof FQualifiedElementRef) {
      return map((FQualifiedElementRef) francaExpression);
    }

    return null;
  }

  public CppValue mapDeploymentDefaultValue(final CppTypeRef cppTypeRef, final FField francaField) {

    String deploymentDefaultValue = deploymentModel.getDefaultValue(francaField);
    if (deploymentDefaultValue == null) {
      return null;
    }

    if (cppTypeRef == CppTypeMapper.STRING_TYPE) {
      return new CppValue("\"" + StringEscapeUtils.escapeJava(deploymentDefaultValue) + "\"");
    } else if (cppTypeRef.getRefersToEnumType()) {
      NameRules nameRules =
          CppNameResolver.selectNameRules(
              deploymentModel.isExternalType(francaField.getType().getDerived()));
      String name = nameRules.getConstantName(deploymentDefaultValue);
      return new CppValue(cppTypeRef.name + "::" + name);
    } else {
      return new CppValue(deploymentDefaultValue);
    }
  }

  private CppValue map(final FQualifiedElementRef francaElementRef) {

    FEvaluableElement value = francaElementRef.getElement();
    if (!(value instanceof FEnumerator)) {
      return null;
    }

    return new CppValue(nameResolver.getFullyQualifiedName(value));
  }
}
