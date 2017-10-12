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

package com.here.ivi.api.model.cppmodel;

import java.util.stream.Stream;

public final class CppConstant extends CppElement {

  public final CppTypeRef type;
  public final CppValue value;

  public CppConstant(final String name, final CppTypeRef type, final CppValue value) {
    this(name, name, type, value);
  }

  public CppConstant(
      final String name,
      final String fullyQualifiedName,
      final CppTypeRef type,
      final CppValue value) {
    super(name, fullyQualifiedName);
    this.type = type;
    this.value = value;
  }

  @Override
  public Stream<? extends CppElement> stream() {
    return Stream.of(type, value);
  }
}
