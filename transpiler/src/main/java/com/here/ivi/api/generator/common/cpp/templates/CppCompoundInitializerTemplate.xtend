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

package com.here.ivi.api.generator.common.cpp.templates

import com.here.ivi.api.generator.common.cpp.CppValueMapper
import com.here.ivi.api.model.cppmodel.CppTypeRef
import org.franca.core.franca.FCompoundInitializer

class CppCompoundInitializerTemplate {
    static def generate(CppTypeRef type, FCompoundInitializer fci) '''
      []() {
        «type.name» tmp;
        «FOR elem : fci.getElements()»
        tmp.«elem.getElement().getName()» = «CppValueMapper.map(type,elem.getValue()).name»;
        «ENDFOR»
        return tmp;
      }()'''
}
