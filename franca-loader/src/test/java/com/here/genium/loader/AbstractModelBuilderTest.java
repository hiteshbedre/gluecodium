/*
 * Copyright (C) 2016-2019 HERE Europe B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 * License-Filename: LICENSE
 */

package com.here.genium.loader;

import com.here.genium.common.ModelBuilderContextStack;
import com.here.genium.test.MockContextStack;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class AbstractModelBuilderTest {

  private final Object resultObject = new Object();

  private final MockContextStack<Object> contextStack = new MockContextStack<>();
  private final TestableModelBuilder modelBuilder = new TestableModelBuilder(contextStack);

  private static class TestableModelBuilder extends AbstractModelBuilder<Object> {
    TestableModelBuilder(ModelBuilderContextStack<Object> contextStack) {
      super(contextStack);
    }
  }

  @Test
  public void closeContextPropagatesResults() {
    contextStack.getCurrentContext().getCurrentResults().add(resultObject);

    modelBuilder.closeContext();

    Assert.assertTrue(contextStack.getParentContext().getPreviousResults().contains(resultObject));
  }

  @Test
  public void closeContextAppendsResults() {
    final Object anotherResultObject = new Object();
    contextStack.getCurrentContext().getCurrentResults().add(anotherResultObject);
    contextStack.getParentContext().getPreviousResults().add(resultObject);

    modelBuilder.closeContext();

    Assert.assertTrue(contextStack.getParentContext().getPreviousResults().contains(resultObject));
    Assert.assertTrue(
        contextStack.getParentContext().getPreviousResults().contains(anotherResultObject));
  }
}