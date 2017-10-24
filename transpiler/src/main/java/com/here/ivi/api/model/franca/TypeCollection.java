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

package com.here.ivi.api.model.franca;

import org.franca.core.franca.FModel;
import org.franca.core.franca.FTypeCollection;
import org.franca.deploymodel.core.MappingGenericPropertyAccessor;

public class TypeCollection extends FrancaElement {

  private final FTypeCollection francaTypeCollection;

  public TypeCollection(
      final FTypeCollection francaTypeCollection,
      final MappingGenericPropertyAccessor accessor,
      final FModel francaModel) {
    super(accessor, francaModel);
    this.francaTypeCollection = francaTypeCollection;
  }

  @Override
  public FTypeCollection getFrancaTypeCollection() {
    return francaTypeCollection;
  }
}
