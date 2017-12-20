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

package com.here.ivi.api.model.java;

import com.here.ivi.api.model.common.Streamable;

public abstract class JavaElement extends Streamable<JavaElement> {

  public final String name;
  public String comment = "";
  public JavaVisibility visibility = JavaVisibility.PACKAGE;

  public JavaElement(final String name) {
    super();
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}