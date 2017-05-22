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

import java.util.Objects;
import java.util.stream.Stream;

public class JavaElement {
  public String name;
  public String comment = null;

  public JavaElement() {}

  public JavaElement(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "." + name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    JavaElement that = (JavaElement) o;

    return name != null ? !name.equals(that.name) : that.name != null;
  }

  @Override
  public int hashCode() {
    return name != null ? name.hashCode() : 0;
  }

  public Stream<JavaElement> stream() {
    return Stream.empty();
  }

  public final Stream<JavaElement> streamRecursive() {
    return Stream.concat(
        Stream.of(this), stream().filter(Objects::nonNull).flatMap(JavaElement::streamRecursive));
  }
}
