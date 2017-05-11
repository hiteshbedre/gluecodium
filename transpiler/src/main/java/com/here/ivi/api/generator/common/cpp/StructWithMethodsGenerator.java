package com.here.ivi.api.generator.common.cpp;

import com.google.common.collect.Iterables;
import com.here.ivi.api.generator.common.GeneratedFile;
import com.here.ivi.api.generator.common.GeneratorSuite;
import com.here.ivi.api.generator.common.NameHelper;
import com.here.ivi.api.generator.common.templates.CppCommentHeaderTemplate;
import com.here.ivi.api.generator.common.templates.CppDelegatorTemplate;
import com.here.ivi.api.generator.common.templates.CppStructWithMethodsTemplate;
import com.here.ivi.api.generator.cppstub.templates.StructCtor;
import com.here.ivi.api.model.DefaultValuesHelper;
import com.here.ivi.api.model.FrancaModel;
import com.here.ivi.api.model.StructMethodHelper;
import com.here.ivi.api.model.cppmodel.*;
import navigation.CppStubSpec;
import org.franca.core.franca.*;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class StructWithMethodsGenerator {

    static Logger logger = Logger.getLogger(StructWithMethodsGenerator.class.getName());

    private final CppNameRules nameRules;

    public StructWithMethodsGenerator(CppNameRules rules) {
        this.nameRules = rules;
    }

    public GeneratedFile generate(final GeneratorSuite<?,?> suite,
                                  final FrancaModel<? extends CppStubSpec.InterfacePropertyAccessor,
                                                         ? extends CppStubSpec.TypeCollectionPropertyAccessor> model,
                                  final FrancaModel.Interface<? extends CppStubSpec.InterfacePropertyAccessor> methods,
                                  final FrancaModel.TypeCollection<? extends CppStubSpec.TypeCollectionPropertyAccessor> tc) {

        CppNamespace ns = generateCppModel(methods, tc, model);

        CppIncludeResolver resolver = new CppIncludeResolver(model, tc, nameRules);
        resolver.resolveLazyIncludes(ns);

        List<String> directories = nameRules.packageToDirectoryStructure(tc.getPackage());
        String outputFile = nameRules.typeCollectionTarget(directories, tc);

        CharSequence generatorNotice = CppGeneratorHelper.generateGeneratorNotice(suite, tc, outputFile);
        CharSequence innerContent = CppDelegatorTemplate.generate(new CppTemplateDelegator() {
            public CharSequence generate(CppClass cppClass) {
                return CppStructWithMethodsTemplate.generate(this, cppClass);
            }
        }, ns);
        String fileContent = CppCommentHeaderTemplate.generate(generatorNotice, innerContent).toString();

        return new GeneratedFile(fileContent, outputFile);
    }

    private CppNamespace generateCppModel(FrancaModel.Interface<? extends CppStubSpec.InterfacePropertyAccessor> methods,
                                          FrancaModel.TypeCollection<? extends CppStubSpec.TypeCollectionPropertyAccessor> tc,
                                          FrancaModel<? extends CppStubSpec.InterfacePropertyAccessor,
                                                  ? extends CppStubSpec.TypeCollectionPropertyAccessor> model) {

        List<CppNamespace> packageNs = CppGeneratorHelper.packageToCppNamespace(nameRules.packageToNamespace(tc.getPackage()));

        CppClass newClass = generateClass(methods, tc, model);

        // add to innermost namespace
        Iterables.getLast(packageNs).members.add(newClass);

        // return outermost namespace
        return Iterables.getFirst(packageNs, null);
    }

    private CppClass generateClass(final FrancaModel.Interface<? extends CppStubSpec.InterfacePropertyAccessor> api,
                                   final FrancaModel.TypeCollection<? extends CppStubSpec.TypeCollectionPropertyAccessor> tc,
                                   final FrancaModel<? extends CppStubSpec.InterfacePropertyAccessor,
                                           ? extends CppStubSpec.TypeCollectionPropertyAccessor> model) {

        CppModelAccessor<CppStubSpec.TypeCollectionPropertyAccessor> rootType =
                new CppModelAccessor<>(tc.fTypeCollection, tc.model.fModel, tc.accessor,  nameRules, model);

        CppClass newClass = new CppClass(nameRules.structName(tc.getName()));

        // nested enums //////////////////////////
        for (FType type : tc.fTypeCollection.getTypes()) {
            if (type instanceof FEnumerationType) {
                newClass.enums.add(TypeGenerationHelper.buildCppEnumClass(nameRules, (FEnumerationType) type));
            }
        }

        // find member struct ///////////////////////////
        FStructType memberStruct = StructMethodHelper.findStructType(tc);

        if (memberStruct == null) {
            logger.severe("Failed to find type struct! ");
            return newClass;
        }

        // default values of members //////////////////////////
        FCompoundInitializer defaultInitializer = null;
        for (FConstantDef constantDef : tc.fTypeCollection.getConstants()) {
            // only structs of the same type as belonging interface with correct name will be checked
            if (DefaultValuesHelper.isStructDefaultValueConstant(constantDef) &&
                    StructMethodHelper.isBelongingStruct(constantDef)) {
                // is valid as constantDef was parsed as a struct ...
                defaultInitializer = (FCompoundInitializer) constantDef.getRhs();
                break;
            }
        }

        if (defaultInitializer == null) {
            logger.severe("Failed to find default values of " + memberStruct.getName());
            return newClass;
        }

        // generate fields /////////////////////////////////
        Iterator<FField> memberIterator = memberStruct.getElements().iterator();
        Iterator<FFieldInitializer> valueIterator = defaultInitializer.getElements().iterator();
        while (memberIterator.hasNext() && valueIterator.hasNext()) {
            CppField field = TypeGenerationHelper.buildCppField(nameRules, rootType, memberIterator.next(), valueIterator.next());
            newClass.fields.add(field);
        }

        // methods ////////////////////////////
        //////////////////////////////////////

        // default constructor is added via xtend template ...

        generateNonDefaultConstructors(newClass, api, model);

        // constants
        for (FConstantDef constantDef : tc.fTypeCollection.getConstants()) {

            // skip all default values in the generation
            if (DefaultValuesHelper.isStructDefaultValueConstant(constantDef)) {
                continue;
            }

            CppConstant constant = TypeGenerationHelper.buildCppConstant(nameRules, rootType, constantDef);
            if (constant.isValid()) {
                newClass.constants.add(constant);
            } else {
                logger.severe("Failed generating constant! " + constantDef.getName() + " " + constantDef.getRhs().getClass());
            }
        }

        return newClass;
    }

    private void generateNonDefaultConstructors(CppClass newClass,
                                                final FrancaModel.Interface<? extends CppStubSpec.InterfacePropertyAccessor> api,
                                                final FrancaModel<? extends CppStubSpec.InterfacePropertyAccessor,
                                                        ? extends CppStubSpec.TypeCollectionPropertyAccessor> model) {
        if (api == null) {
            return;
        }

        CppModelAccessor<CppStubSpec.InterfacePropertyAccessor> rootModelIf =
                new CppModelAccessor<>(api.fInterface, api.model.fModel, api.accessor, nameRules, model);

        StructCtor templateCtor = new StructCtor();
        api.fInterface.getMethods()
                .stream()
                .filter(StructMethodHelper::isStructInitializer)
                .forEach(method -> {
                            CppMethod nonDefaultCtor = new CppMethod();
                            nonDefaultCtor.mbt = templateCtor;
                            nonDefaultCtor.name = newClass.name;
                            nonDefaultCtor.returnType = CppType.None;

                            final List<FArgument> inArgs = method.getInArgs();
                            if (inArgs.size() == 1) {
                                nonDefaultCtor.specifiers.add(CppMethod.Specifier.EXPLICIT);
                            }

                            for (FArgument arg : method.getInArgs()) {
                                CppParameter param = new CppParameter();
                                param.name = NameHelper.toSnakeCase(arg.getName());
                                param.type = CppTypeMapper.map(rootModelIf, arg);
                                param.mode = CppParameter.Mode.Input;
                                nonDefaultCtor.inParameters.add(param);
                            }

                            newClass.methods.add(nonDefaultCtor);
                        }
                );
    }

}
