/*
 * Copyright (C) 2016-2020 HERE Europe B.V.
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

package com.here.gluecodium.generator.common

import com.here.gluecodium.model.lime.LimeAttributeType
import com.here.gluecodium.model.lime.LimeAttributeType.SKIP
import com.here.gluecodium.model.lime.LimeAttributeValueType.TAG
import com.here.gluecodium.model.lime.LimeContainerWithInheritance
import com.here.gluecodium.model.lime.LimeFunction
import com.here.gluecodium.model.lime.LimeInterface
import com.here.gluecodium.model.lime.LimeNamedElement
import com.here.gluecodium.model.lime.LimeStruct
import com.here.gluecodium.model.lime.LimeTypeHelper

/**
 * Predicates used by `ifPredicate`/`unlessPredicate` template helpers in several generators.
 */
internal object CommonGeneratorPredicates {
    fun hasAnyComment(limeElement: Any, platformTag: String) =
        when (limeElement) {
            is LimeFunction -> limeElement.run {
                comment.getFor(platformTag).isNotBlank() || comment.isExcluded ||
                        returnType.comment.getFor(platformTag).isNotBlank() ||
                        (thrownType?.comment?.getFor(platformTag)?.isEmpty() == false) ||
                        attributes.have(LimeAttributeType.DEPRECATED) ||
                        parameters.any { it.comment.getFor(platformTag).isNotBlank() }
            }
            is LimeNamedElement -> limeElement.run {
                comment.getFor(platformTag).isNotBlank() || comment.isExcluded ||
                        attributes.have(LimeAttributeType.DEPRECATED)
            }
            else -> false
        }

    fun hasImmutableFields(limeStruct: Any) =
        when {
            limeStruct !is LimeStruct -> false
            limeStruct.attributes.have(LimeAttributeType.IMMUTABLE) -> true
            else -> limeStruct.fields
                .flatMap { LimeTypeHelper.getAllFieldTypes(it.typeRef.type) }
                .any { it.attributes.have(LimeAttributeType.IMMUTABLE) }
        }

    fun hasSkipTags(limeElement: LimeNamedElement, tags: Set<String>) =
        when (val skipTags = limeElement.attributes.get(SKIP, TAG, Any::class.java)) {
            is String -> tags.contains(skipTags)
            is List<*> -> skipTags.filterIsInstance<String>().intersect(tags).isNotEmpty()
            else -> false
        }

    fun hasTypeRepository(limeContainer: Any) =
        when {
            limeContainer !is LimeContainerWithInheritance -> false
            limeContainer is LimeInterface -> true
            limeContainer.visibility.isOpen -> true
            else -> limeContainer.parents.isNotEmpty()
        }
}
