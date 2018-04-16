/*
 * Copyright (c) 2016-2018 HERE Europe B.V.
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

package com.here.genium.platform.baseapi;

import com.google.common.annotations.VisibleForTesting;
import com.here.genium.cli.OptionReader;
import com.here.genium.common.CollectionsHelper;
import com.here.genium.generator.common.GeneratedFile;
import com.here.genium.generator.common.modelbuilder.FrancaTreeWalker;
import com.here.genium.generator.cpp.CppGenerator;
import com.here.genium.generator.cpp.CppModelBuilder;
import com.here.genium.generator.cpp.CppNameRules;
import com.here.genium.generator.cpp.CppTypeMapper;
import com.here.genium.model.common.Include;
import com.here.genium.model.common.Streamable;
import com.here.genium.model.cpp.*;
import com.here.genium.model.franca.DefinedBy;
import com.here.genium.model.franca.FrancaDeploymentModel;
import com.here.genium.platform.common.GeneratorSuite;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.franca.core.franca.FTypeCollection;

/**
 * This generator will build all the BaseApis that will have to be implemented on the client
 * side @ref CppMapper as well as the data used by @ref TypeCollectionMapper.
 *
 * <p>It is the underlying generator, that all others depend on, as they will invoke the actual
 * implementation through the C++ interfaces.
 */
public final class BaseApiGeneratorSuite extends GeneratorSuite {

  public static final String GENERATOR_NAME = "cpp";

  @VisibleForTesting
  static final List<String> ADDITIONAL_HEADERS = Arrays.asList("EnumHash", "Return");

  private final String internalNamespace;

  public BaseApiGeneratorSuite(final OptionReader.GeniumOptions options) {
    super();
    internalNamespace = options.getCppInternalNamespace();
  }

  @Override
  public String getName() {
    return "com.here.BaseApiGenerator";
  }

  public List<GeneratedFile> generate(
      final FrancaDeploymentModel deploymentModel, final List<FTypeCollection> typeCollections) {

    CppIncludeResolver includeResolver = new CppIncludeResolver(deploymentModel);
    CppTypeMapper typeMapper = new CppTypeMapper(includeResolver, internalNamespace);

    CppGenerator generator =
        new CppGenerator(BaseApiGeneratorSuite.GENERATOR_NAME, internalNamespace);

    List<GeneratedFile> generatedFiles = new LinkedList<>();
    for (final FTypeCollection francaTypeCollection : typeCollections) {

      CppFile cppModel =
          mapFrancaTypeCollectionToCppModel(deploymentModel, typeMapper, francaTypeCollection);
      String outputFilePathHeader = CppNameRules.getOutputFilePath(francaTypeCollection);
      String outputFilePathImpl = CppNameRules.getOutputFilePath(francaTypeCollection);

      generatedFiles.addAll(
          generator.generateCode(cppModel, outputFilePathHeader, outputFilePathImpl));
    }

    for (String header : ADDITIONAL_HEADERS) {
      generatedFiles.add(generator.generateHelperHeader(header));
    }

    return generatedFiles;
  }

  private static CppFile mapFrancaTypeCollectionToCppModel(
      final FrancaDeploymentModel deploymentModel,
      final CppTypeMapper typeMapper,
      final FTypeCollection francaTypeCollection) {

    CppModelBuilder builder = new CppModelBuilder(deploymentModel, typeMapper);
    FrancaTreeWalker treeWalker = new FrancaTreeWalker(Collections.singletonList(builder));

    treeWalker.walkTree(francaTypeCollection);

    CppFile cppModel = new CppFile(DefinedBy.getPackages(francaTypeCollection));
    cppModel.members.addAll(builder.getFinalResults());
    cppModel.includes.addAll(collectIncludes(cppModel));
    cppModel.forwardDeclarations.addAll(collectForwardDeclarations(cppModel));

    return cppModel;
  }

  private static List<Include> collectIncludes(final CppFile cppModel) {
    Stream<Streamable> allElementsStream =
        cppModel.members.stream().flatMap(CppElement::streamRecursive);
    return CollectionsHelper.getStreamOfType(allElementsStream, CppElementWithIncludes.class)
        .map(elementWithIncludes -> elementWithIncludes.includes)
        .flatMap(Set::stream)
        .collect(Collectors.toList());
  }

  private static List<CppForwardDeclaration> collectForwardDeclarations(final CppFile cppModel) {
    Stream<Streamable> allElementsStream =
        cppModel.members.stream().flatMap(CppElement::streamRecursive);
    return CollectionsHelper.getStreamOfType(allElementsStream, CppInstanceTypeRef.class)
        .map(instanceTypeRef -> instanceTypeRef.name)
        .map(CppForwardDeclaration::new)
        .collect(Collectors.toList());
  }
}