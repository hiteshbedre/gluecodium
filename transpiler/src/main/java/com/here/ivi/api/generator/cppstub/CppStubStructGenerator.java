package com.here.ivi.api.generator.cppstub;

import com.google.common.collect.Iterables;
import com.here.ivi.api.generator.common.*;
import com.here.ivi.api.generator.common.templates.CppCommentHeaderTemplate;
import com.here.ivi.api.generator.common.CppNameRules;
import com.here.ivi.api.generator.common.templates.CppDelegatorTemplate;
import com.here.ivi.api.generator.common.templates.CppStructWithMethodsTemplate;
import com.here.ivi.api.generator.cppstub.templates.StructCtor;
import com.here.ivi.api.model.DefaultValuesHelper;
import com.here.ivi.api.model.FrancaModel;
import com.here.ivi.api.model.StructMethodHelper;
import com.here.ivi.api.model.cppmodel.*;
import navigation.CppStubSpec;
import org.franca.core.franca.*;

import java.util.*;
import java.util.logging.Logger;


public class CppStubStructGenerator {

    static Logger logger = Logger.getLogger(CppStubStructGenerator.class.getName());

    private final CppNameRules nameRules;

    public CppStubStructGenerator(CppNameRules rules) {
        this.nameRules = rules;
    }

    public GeneratedFile generateFiles(final GeneratorSuite<?,?> suite,
                                       final FrancaModel<? extends CppStubSpec.InterfacePropertyAccessor,
                                                         ? extends CppStubSpec.TypeCollectionPropertyAccessor> model,
                                       final FrancaModel.Interface<? extends CppStubSpec.InterfacePropertyAccessor> methods,
                                       final FrancaModel.TypeCollection<? extends CppStubSpec.TypeCollectionPropertyAccessor> tc) {

        CppNamespace ns = generateCppModel(methods, tc, model);

        CppIncludeResolver resolver = new CppIncludeResolver(model, tc, nameRules);
        resolver.resolveLazyIncludes(ns);

        List<String> directories = nameRules.packageToDirectoryStructure(tc.getPackage());
        String outputFile =  nameRules.typeCollectionTarget(directories, tc);

        CharSequence generatorNotice = CppGeneratorHelper.generateGeneratorNotice(suite, tc, outputFile);
        CharSequence innerContent = CppDelegatorTemplate.generate(new CppTemplateDelegator() {
            public CharSequence generate(CppClass cppClass) {
                return CppStructWithMethodsTemplate.generate(cppClass);
            }
        }, ns);
        String fileContent = CppCommentHeaderTemplate.generate(generatorNotice, innerContent).toString();

        return new GeneratedFile(fileContent, outputFile);
    }

    private CppField generateCppField(CppModelAccessor<CppStubSpec.TypeCollectionPropertyAccessor> rootType,
                                      FField ffield, FFieldInitializer initializer) {

        FTypeRef typeRef = ffield.getType();
        CppField field = new CppField();
        field.name = nameRules.fieldName(ffield.getName());
        field.type = CppTypeMapper.map(rootType, typeRef);
        field.initializer = CppValueMapper.map(field.type, initializer.getValue());
        return field;
    }

    private CppNamespace generateCppModel(FrancaModel.Interface<? extends CppStubSpec.InterfacePropertyAccessor> methods,
                                          FrancaModel.TypeCollection<? extends CppStubSpec.TypeCollectionPropertyAccessor> tc,
                                          FrancaModel<? extends CppStubSpec.InterfacePropertyAccessor,
                                                  ? extends CppStubSpec.TypeCollectionPropertyAccessor> model) {

        List<CppNamespace> packageNs = CppGeneratorHelper.packageToNamespace(tc.getPackage());

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

        // find member struct ///////////////////////////

        // search for a struct inside the type collection of name StructName
        FStructType memberStruct = StructMethodHelper.findStructType(tc);

        if (memberStruct == null) {
            logger.severe("Failed to find type struct! ");
            return newClass;
        }

        // default values //////////////////////////

        // search for constants from type collection
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
            CppField field = generateCppField(rootType, memberIterator.next(), valueIterator.next());
            newClass.fields.add(field);
        }

        // methods ////////////////////////////
        //////////////////////////////////////

        // default constructor is added via xtend template ...

        if (api != null) {
            CppModelAccessor<CppStubSpec.InterfacePropertyAccessor> rootModelIf =
                    new CppModelAccessor<>(api.fInterface, api.model.fModel, api.accessor, nameRules, model);

            //non default-constructors ...
            StructCtor templateCtor = new StructCtor();
            api.fInterface.getMethods()
                    .stream()
                    .filter(StructMethodHelper::isStructInitializer)
                    .forEach(method -> {
                                CppMethod nonDefaultCtor = new CppMethod();
                                nonDefaultCtor.mbt = templateCtor;
                                nonDefaultCtor.name = newClass.name;
                                nonDefaultCtor.returnType = CppType.None;
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

        return newClass;
    }

}
