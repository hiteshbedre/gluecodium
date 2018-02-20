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

package com.here.ivi.api.generator.cbridge;

import static com.here.ivi.api.generator.cbridge.CBridgeNameRules.CBRIDGE_INTERNAL;
import static com.here.ivi.api.generator.cbridge.CBridgeNameRules.INCLUDE_DIR;

import com.google.common.collect.Iterables;
import com.here.ivi.api.model.cbridge.*;
import com.here.ivi.api.model.common.Include;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public final class CBridgeComponents {

  public static final String PROXY_CACHE_FILENAME =
      Paths.get(CBRIDGE_INTERNAL, INCLUDE_DIR, "CachedProxyBase.h").toString();

  @SuppressWarnings("OperatorWrap")
  public static Collection<Include> collectImplementationIncludes(CInterface cInterface) {
    Collection<Include> includes = new LinkedList<>();
    for (CFunction function :
        Iterables.concat(cInterface.functions, cInterface.inheritedFunctions)) {
      includes.addAll(collectFunctionBodyIncludes(function));
    }
    for (CStruct struct : cInterface.structs) {
      includes.addAll(struct.mappedType.includes);
      for (CField field : struct.fields) {
        includes.addAll(field.type.includes);
      }
    }
    if (cInterface.selfType != null) {
      includes.addAll(cInterface.selfType.includes);
    }

    return includes;
  }

  public static Collection<Include> collectPrivateHeaderIncludes(CInterface cInterface) {
    Collection<Include> includes = new LinkedList<>();
    for (CStruct struct : cInterface.structs) {
      includes.addAll(struct.mappedType.includes);
    }
    if (cInterface.selfType != null) {
      includes.addAll(cInterface.selfType.includes);
    }
    return includes;
  }

  public static Collection<Include> collectHeaderIncludes(CInterface cInterface) {
    Collection<Include> includes = new LinkedList<>();
    for (CFunction function : cInterface.functions) {
      includes.addAll(collectFunctionSignatureIncludes(function));
    }
    for (CStruct struct : cInterface.structs) {
      for (CField field : struct.fields) {
        includes.addAll(field.type.functionReturnType.includes);
        for (CType type : field.type.cTypesNeededByConstructor) {
          includes.addAll(type.includes);
        }
      }
    }
    for (CEnum enumType : cInterface.enumerators) {
      includes.addAll(enumType.includes);
    }

    return includes;
  }

  public static Collection<Include> collectImplementationIncludes(final Collection<CArray> arrays) {
    return arrays.stream().flatMap(array -> array.includes().stream()).collect(Collectors.toList());
  }

  public static Collection<Include> collectHeaderIncludes(final Collection<CArray> arrays) {
    return arrays
        .stream()
        .flatMap(array -> array.returnTypeIncludes().stream())
        .collect(Collectors.toList());
  }

  private static Collection<Include> collectFunctionSignatureIncludes(CFunction function) {
    Collection<Include> includes = new LinkedList<>();
    for (CParameter parameter : function.parameters) {
      includes.addAll(parameter.getSignatureIncludes());
    }
    includes.addAll(function.returnType.functionReturnType.includes);
    if (function.error != null) {
      includes.addAll(function.error.functionReturnType.includes);
    }
    return includes;
  }

  private static Collection<Include> collectFunctionBodyIncludes(CFunction function) {
    Collection<Include> includes = new LinkedList<>();
    for (CParameter parameter : function.parameters) {
      includes.addAll(parameter.mappedType.includes);
    }
    includes.addAll(function.returnType.includes);
    includes.addAll(function.delegateCallIncludes);
    if (function.selfParameter != null) {
      includes.addAll(function.selfParameter.mappedType.includes);
    }
    return includes;
  }
}
