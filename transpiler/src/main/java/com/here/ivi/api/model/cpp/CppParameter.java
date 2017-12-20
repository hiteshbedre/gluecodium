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

package com.here.ivi.api.model.cpp;

import java.util.stream.Stream;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class CppParameter extends CppElement {

  public final CppTypeRef type;
  public final boolean isOutput;

  public CppParameter(final String name, final CppTypeRef type) {
    this(name, type, false);
  }

  public CppParameter(final String name, final CppTypeRef type, final boolean isOutput) {
    super(name);
    this.type = type;
    this.isOutput = isOutput;
  }

  @Override
  public Stream<? extends CppElement> stream() {
    return Stream.of(type);
  }
}