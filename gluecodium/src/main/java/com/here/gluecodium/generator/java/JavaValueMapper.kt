/*
 * Copyright (C) 2016-2019 HERE Europe B.V.
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

package com.here.gluecodium.generator.java

import com.here.gluecodium.model.java.JavaImport
import com.here.gluecodium.model.java.JavaPackage
import com.here.gluecodium.model.java.JavaPrimitiveType
import com.here.gluecodium.model.java.JavaTemplateType
import com.here.gluecodium.model.java.JavaType
import com.here.gluecodium.model.java.JavaValue
import com.here.gluecodium.model.lime.LimeBasicType
import com.here.gluecodium.model.lime.LimeBasicType.TypeId
import com.here.gluecodium.model.lime.LimeContainer
import com.here.gluecodium.model.lime.LimeElement
import com.here.gluecodium.model.lime.LimeTypeHelper
import com.here.gluecodium.model.lime.LimeTypesCollection
import com.here.gluecodium.model.lime.LimeValue

class JavaValueMapper(
    private val limeReferenceMap: Map<String, LimeElement>,
    private val nameRules: JavaNameRules,
    private val typeMapper: JavaTypeMapper
) {
    fun mapValue(limeValue: LimeValue, javaType: JavaType): JavaValue =
        when (limeValue) {
            is LimeValue.Literal -> {
                val limeType = LimeTypeHelper.getActualType(limeValue.typeRef.type)
                val suffix = when {
                    limeType !is LimeBasicType -> ""
                    limeType.typeId == TypeId.FLOAT -> "f"
                    limeType.typeId == TypeId.UINT32 || limeType.typeId == TypeId.UINT64 ||
                            limeType.typeId == TypeId.INT64 -> "L"
                    else -> ""
                }
                JavaValue(limeValue.toString() + suffix, isCustom = true)
            }
            is LimeValue.Enumerator -> {
                val limeEnumerator = limeValue.valueRef.enumerator
                val limeEnumeration = LimeTypeHelper.getActualType(limeValue.typeRef.type)
                var names = listOf(nameRules.getName(limeEnumeration)) +
                        nameRules.getName(limeEnumerator)
                val parentContainer =
                    limeReferenceMap[limeEnumeration.path.parent.toString()] as? LimeContainer
                if (parentContainer != null && parentContainer !is LimeTypesCollection) {
                    names = listOf(nameRules.getName(parentContainer)) + names
                }
                JavaValue(names.joinToString("."), isCustom = true)
            }
            is LimeValue.Special -> mapSpecialValue(limeValue)
            is LimeValue.Null -> JavaValue("null", isCustom = true)
            is LimeValue.InitializerList -> mapInitializerList(limeValue, javaType)
        }

    private fun mapInitializerList(
        limeValue: LimeValue.InitializerList,
        javaType: JavaType
    ): JavaValue {
        val implementationType = (javaType as? JavaTemplateType)?.implementationType ?: javaType
        val prefix = "new ${implementationType.name}"
        val imports = implementationType.imports
        val valuesString = limeValue.values.joinToString(", ") {
            mapValue(it, typeMapper.mapType(it.typeRef)).name
        }

        return if (javaType is JavaTemplateType && valuesString.isNotEmpty()) {
            JavaValue("$prefix(Arrays.asList($valuesString))", imports + ARRAYS_IMPORT, true)
        } else {
            JavaValue("$prefix($valuesString)", imports, true)
        }
    }

    private fun mapSpecialValue(limeValue: LimeValue.Special): JavaValue {
        val prefix = when {
            (limeValue.typeRef.type as LimeBasicType).typeId == TypeId.FLOAT -> "Float"
            else -> "Double"
        }
        val value = when (limeValue.value) {
            LimeValue.Special.ValueId.NAN -> "NaN"
            LimeValue.Special.ValueId.INFINITY -> "POSITIVE_INFINITY"
            LimeValue.Special.ValueId.NEGATIVE_INFINITY -> "NEGATIVE_INFINITY"
        }
        return JavaValue("$prefix.$value", isCustom = true)
    }

    companion object {
        private val ARRAYS_IMPORT = JavaImport("Arrays", JavaPackage(listOf("java", "util")))

        fun inferNextEnumValue(previousEnumValue: JavaValue?) =
            previousEnumValue?.let {
                JavaValue((Integer.parseInt(it.name) + 1).toString())
            } ?: JavaValue("0")

        fun mapNullValue(javaType: JavaType) = JavaValue("(${javaType.name})null")

        fun mapDefaultValue(javaType: JavaType) =
            when (javaType) {
                JavaPrimitiveType.BOOL -> JavaValue("false")
                is JavaPrimitiveType -> JavaValue(decorateLiteralValue(javaType, "0"))
                else -> mapNullValue(javaType)
            }

        private fun decorateLiteralValue(javaType: JavaPrimitiveType, value: String): String {
            val literal = StringBuilder()

            when (javaType) {
                JavaPrimitiveType.BYTE -> literal.append("(byte)")
                JavaPrimitiveType.SHORT -> literal.append("(short)")
            }
            literal.append(value)
            when (javaType) {
                JavaPrimitiveType.FLOAT -> literal.append('f')
                JavaPrimitiveType.LONG -> literal.append('L')
            }

            return literal.toString()
        }
    }
}