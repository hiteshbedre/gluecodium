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

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Stream;

public class JavaClass extends JavaElement {
  public Set<JavaMethod> methods = new LinkedHashSet<>();
  public Set<JavaField> fields = new LinkedHashSet<>();
  public Set<JavaInheritance> inheritances = new LinkedHashSet<>();
  public Set<JavaConstant> constants = new LinkedHashSet<>();
  public Set<JavaEnum> enums = new LinkedHashSet<>();

  public JavaClass(String name) {
    super(name);
  }

  @Override
  public Stream<JavaElement> stream() {
    return Stream.concat(
            methods.stream(),
            Stream.concat(
                fields.stream(),
                Stream.concat(
                    constants.stream(), Stream.concat(enums.stream(), inheritances.stream()))))
        .map(JavaElement.class::cast);
  }
}
