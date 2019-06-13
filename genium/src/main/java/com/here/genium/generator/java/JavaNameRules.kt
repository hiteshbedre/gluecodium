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

import com.here.genium.generator.common.NameRuleSet
import com.here.genium.generator.common.NameRules
import com.here.genium.model.lime.LimeContainer
import com.here.genium.model.lime.LimeEnumeration

class JavaNameRules(nameRuleSet: NameRuleSet) : NameRules(nameRuleSet) {
    fun getImplementationClassName(limeContainer: LimeContainer) = getName(limeContainer) + "Impl"

    fun getExceptionName(limeEnum: LimeEnumeration) = getName(limeEnum) + "Exception"

    companion object {
        fun getPackageName(base: String) = base.replace("_", "")
    }
}
