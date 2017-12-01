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

package com.here.ivi.api.generator.swift;

import com.google.common.annotations.VisibleForTesting;
import com.here.ivi.api.generator.common.FrancaTreeWalker;
import com.here.ivi.api.generator.common.GeneratedFile;
import com.here.ivi.api.generator.common.GeneratorSuite;
import com.here.ivi.api.generator.common.TemplateEngine;
import com.here.ivi.api.model.franca.FrancaDeploymentModel;
import com.here.ivi.api.model.swift.SwiftFile;
import java.util.*;
import org.franca.core.franca.FTypeCollection;

public class SwiftGenerator {

  public final SwiftArrayGenerator arrayGenerator = new SwiftArrayGenerator();
  public static final List<GeneratedFile> STATIC_FILES =
      Arrays.asList(
          GeneratorSuite.copyTarget("swift/RefHolder.swift", ""),
          GeneratorSuite.copyTarget("swift/CollectionOf.swift", ""));

  private final FrancaDeploymentModel deploymentModel;

  public SwiftGenerator(final FrancaDeploymentModel deploymentModel) {
    this.deploymentModel = deploymentModel;
  }

  public GeneratedFile generate(final FTypeCollection francaTypeCollection) {
    SwiftFile file = buildSwiftModel(francaTypeCollection);
    if (file.isEmpty()) {
      return null;
    } else {
      return new GeneratedFile(
          TemplateEngine.render("swift/File", file),
          SwiftNameRules.getImplementationFileName(francaTypeCollection));
    }
  }

  @VisibleForTesting
  SwiftFile buildSwiftModel(final FTypeCollection francaTypeCollection) {
    SwiftModelBuilder modelBuilder = new SwiftModelBuilder(deploymentModel);
    FrancaTreeWalker treeWalker = new FrancaTreeWalker(Collections.singletonList(modelBuilder));

    treeWalker.walkTree(francaTypeCollection);
    arrayGenerator.collect(modelBuilder.arraysCollector);
    return modelBuilder.getFinalResult(SwiftFile.class);
  }
}
