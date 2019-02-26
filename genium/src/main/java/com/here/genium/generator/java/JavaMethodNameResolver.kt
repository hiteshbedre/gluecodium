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

package com.here.genium.generator.java

import com.here.genium.common.FrancaSignatureResolver
import org.franca.core.franca.FMethod
import org.franca.core.franca.FTypeRef

open class JavaMethodNameResolver : FrancaSignatureResolver() {

    open fun getName(francaMethod: FMethod): String {
        val selector = if (hasSignatureClash(francaMethod)) {
            francaMethod.selector
        } else {
            ""
        }
        return JavaNameRules.getMethodName(francaMethod.name, selector)
    }

    override fun getArrayName(elementType: FTypeRef) = TYPE_ERASED_ARRAY

    override fun getMapName(keyType: FTypeRef, valueType: FTypeRef) = TYPE_ERASED_MAP

    companion object {
        private const val TYPE_ERASED_ARRAY = "List<>"
        private const val TYPE_ERASED_MAP = "Map<>"
    }
}