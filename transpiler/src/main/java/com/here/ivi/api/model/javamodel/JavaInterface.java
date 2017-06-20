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

package com.here.ivi.api.model.javamodel;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public final class JavaInterface extends JavaElement {
  public JavaPackage javaPackage = new JavaPackage(Arrays.asList("com", "here", "android"));
  public Set<JavaMethod> methods = new LinkedHashSet<>();
  public Set<JavaInheritance> inheritances = new LinkedHashSet<>();

  public JavaInterface(final String name) {
    super(name);
  }
}
