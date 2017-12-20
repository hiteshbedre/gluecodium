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

package com.here.ivi.api.generator.common.modelbuilder;

import com.here.ivi.api.common.CollectionsHelper;
import java.util.Collections;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.franca.core.franca.*;

@SuppressWarnings({"PMD.TooManyMethods"})
public abstract class AbstractModelBuilder<E> implements ModelBuilder {

  private final ModelBuilderContextStack<E> contextStack;
  private ModelBuilderContext<E> resultContext;

  protected AbstractModelBuilder(final ModelBuilderContextStack<E> contextStack) {
    this.contextStack = contextStack;
  }

  @Override
  public void startBuilding(EObject object) {
    openContext();
  }

  @Override
  public void startBuilding(FInterface francaInterface) {
    openContext();
  }

  @Override
  public void startBuilding(FTypeCollection francaTypeCollection) {
    openContext();
  }

  @Override
  public void startBuildingInputArgument(FArgument francaArgument) {
    openContext();
  }

  @Override
  public void startBuildingOutputArgument(FArgument francaArgument) {
    openContext();
  }

  @Override
  public void finishBuilding(FInterface francaInterface) {
    closeContext();
  }

  @Override
  public void finishBuilding(FTypeCollection francaTypeCollection) {
    closeContext();
  }

  @Override
  public void finishBuilding(FMethod francaMethod) {
    closeContext();
  }

  @Override
  public void finishBuildingInputArgument(FArgument francaArgument) {
    closeContext();
  }

  @Override
  public void finishBuildingOutputArgument(FArgument francaArgument) {
    closeContext();
  }

  @Override
  public void finishBuilding(FTypeRef francaTypeRef) {
    closeContext();
  }

  @Override
  public void finishBuilding(FConstantDef francaConstant) {
    closeContext();
  }

  @Override
  public void finishBuilding(FField francaField) {
    closeContext();
  }

  @Override
  public void finishBuilding(FStructType francaStructType) {
    closeContext();
  }

  @Override
  public void finishBuilding(FTypeDef francaTypeDef) {
    closeContext();
  }

  @Override
  public void finishBuilding(FArrayType francaArrayType) {
    closeContext();
  }

  @Override
  public void finishBuilding(FMapType francaMapType) {
    closeContext();
  }

  @Override
  public void finishBuilding(FEnumerationType francaEnumerationType) {
    closeContext();
  }

  @Override
  public void finishBuilding(FEnumerator francaEnumerator) {
    closeContext();
  }

  @Override
  public void finishBuilding(FExpression francaExpression) {
    closeContext();
  }

  @Override
  public void finishBuilding(FUnionType francaUnionType) {
    closeContext();
  }

  @Override
  public void finishBuilding(FAttribute francaAttribute) {
    closeContext();
  }

  /**
   * Get final results of the model builder execution. Intended to be called by clients of the model
   * builder but not within the model builder itself.
   *
   * @return A list of results
   */
  public List<E> getFinalResults() {
    return resultContext != null ? resultContext.currentResults : Collections.emptyList();
  }

  /**
   * Get the first item of the given type from the list of final results of the model builder
   * execution. Intended to be called by clients of the model builder but not within the model
   * builder itself.
   *
   * @param clazz Class object representing the type of the result to get
   * @param <T> Type of the result to get
   * @return A result item
   */
  public <T extends E> T getFinalResult(final Class<T> clazz) {
    return resultContext != null
        ? CollectionsHelper.getFirstOfType(resultContext.currentResults, clazz)
        : null;
  }

  /**
   * Get the results of the previous ("child") step of model builder execution.
   *
   * @return A list of results
   */
  protected <T extends E> T getPreviousResult(final Class<T> clazz) {
    return CollectionsHelper.getFirstOfType(getCurrentContext().previousResults, clazz);
  }

  /**
   * Get the first item of the given type from the list of results of the previous ("child") step of
   * model builder execution.
   *
   * @param clazz Class object representing the type of the result to get
   * @param <T> Type of the result to get
   * @return A result item
   */
  protected <T extends E> List<T> getPreviousResults(final Class<T> clazz) {
    return CollectionsHelper.getAllOfType(getCurrentContext().previousResults, clazz);
  }

  protected final void openContext() {
    contextStack.openContext();
  }

  protected final void closeContext() {
    resultContext = contextStack.getCurrentContext();
    if (resultContext != null) {
      ModelBuilderContext<E> parentContext = contextStack.getParentContext();
      if (parentContext != null) {
        parentContext.previousResults.addAll(resultContext.currentResults);
      }
    }

    contextStack.closeContext();
  }

  protected final ModelBuilderContext<E> getCurrentContext() {
    return contextStack.getCurrentContext();
  }

  protected final ModelBuilderContext<E> getParentContext() {
    return contextStack.getParentContext();
  }

  protected final void storeResult(final E element) {
    ModelBuilderContext<E> currentContext = contextStack.getCurrentContext();
    if (currentContext != null) {
      currentContext.currentResults.add(element);
    }
  }
}