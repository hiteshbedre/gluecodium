package com.here.ivi.api;

import com.here.ivi.api.model.FrancaModel;
import com.here.ivi.api.model.ModelHelper;
import com.here.ivi.api.loader.FrancaModelLoader;
import com.here.ivi.api.loader.cppstub.CppStubSpecAccessorFactory;
import com.here.ivi.api.generator.common.Version;

import org.franca.core.franca.FModelElement;
import org.franca.core.franca.FMethod;
import org.franca.core.franca.FAttribute;
import org.franca.core.franca.FArgument;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("BooleanMethodIsAlwaysInverted")
public class Fidldif {
    public static void main(final String[] args) {
        if (args.length != 2) return;

        // using the CppStubSpec to keep things simple ...
        final CppStubSpecAccessorFactory specAccessorFactory = new CppStubSpecAccessorFactory();
        final FrancaModelLoader<?, ?> fml = new FrancaModelLoader<>(specAccessorFactory);
        ModelHelper.getFdeplInjector().injectMembers(fml);


        final FrancaModel<?, ?> a = fml.load(specAccessorFactory.getSpecPath(), FrancaModelLoader.listFilesRecursively(new File(args[0])));
        final FrancaModel<?, ?> b = fml.load(specAccessorFactory.getSpecPath(), FrancaModelLoader.listFilesRecursively(new File(args[1])));

        // find removed interfaces
        List<FrancaModel.Interface<?>> removedInterfaces = a.interfaces.stream()
            .filter(f -> !b.interfaces.contains(f)).collect(Collectors.toList());

        // find added interfaces
        List<FrancaModel.Interface<?>> addedInterfaces = b.interfaces.stream()
            .filter(f -> !a.interfaces.contains(f)).collect(Collectors.toList());

        System.out.println("removed interfaces:");
        for (FrancaModel.Interface<?> iface : removedInterfaces) {
            System.out.println("   * " + iface.getName());
        }
        System.out.println("added interfaces:");
        for (FrancaModel.Interface<?> iface : addedInterfaces) {
            System.out.println("   * " + iface.getName());
        }

        int resultCode = 0;

        // compare interfaces
        for (FrancaModel.Interface<?> ifa : a.interfaces) {
            int bndx = b.interfaces.indexOf(ifa);
            if (bndx >= 0) {
                FrancaModel.Interface<?> ifb = b.interfaces.get(bndx);

                // removed methods
                List<FMethod> removedMethods = ifa.fInterface.getMethods().stream()
                    .filter(i -> !findF(i, ifb.fInterface.getMethods())).collect(Collectors.toList());

                removedMethods
                    .forEach(ma -> System.out.println("in " + ifa.getName() + " removed method " + ma.getName()));

                // added methods
                List<FMethod> addedMethods = ifb.fInterface.getMethods().stream()
                    .filter(i -> !findF(i, ifa.fInterface.getMethods())).collect(Collectors.toList());
                addedMethods
                    .forEach(mb -> System.out.println("in " + ifb.getName() + " added method " + mb.getName()));

                // removed attributes
                List<FAttribute> removedAttributes = ifa.fInterface.getAttributes().stream()
                    .filter(i -> !findF(i, ifb.fInterface.getAttributes())).collect(Collectors.toList());
                removedAttributes
                    .forEach(aa -> System.out.println("in " + ifa.getName() + " removed attribute " + aa.getName()));

                // added attributes
                List<FAttribute> addedAttributes = ifb.fInterface.getAttributes().stream()
                    .filter(i -> !findF(i, ifa.fInterface.getAttributes())).collect(Collectors.toList());
                addedAttributes
                    .forEach(ab -> System.out.println("in " + ifb.getName() + " added attribute " + ab.getName()));


                boolean changedArgs = false;
                for (FMethod am : ifa.fInterface.getMethods()) {
                    for (FMethod bm : ifb.fInterface.getMethods()) {
                        if (bm.getName().equals(am.getName())) {
                            if (am.getInArgs().size() == bm.getInArgs().size()
                                && am.getOutArgs().size() == bm.getOutArgs().size()) {

                                if (!compareArguments(am.getName(), am.getInArgs().iterator(), bm.getInArgs().iterator())) {
                                    changedArgs = true;
                                }

                                if (!compareArguments(am.getName(), am.getOutArgs().iterator(), bm.getOutArgs().iterator())) {
                                    changedArgs = true;
                                }
                            }
                            else {
                                System.out.println(am.getName() + " has different argument count");
                                changedArgs = true;
                            }
                        }
                    }
                }

                Version va = ifa.getVersion();
                Version vb = ifb.getVersion();

                // check if major if neccessary
                if ((removedMethods.size() > 0 || removedAttributes.size() > 0 || changedArgs)
                    && va.major >= vb.major) {
                    System.err.println("incompatible change in " + ifb.getName() + "! Major version increase needed");
                    resultCode = -1;
                }

                // same for minor
                if ((addedMethods.size() > 0 || addedAttributes.size() > 0)
                    && (va.major == vb.major && va.minor >= vb.minor)) {
                    System.err.println("elements added in " + ifb.getName() + "! Minor version increase needed");
                    resultCode = -1;
                }

            }
        }
        System.exit(resultCode);
    }

    private static <T extends FModelElement> boolean findF(T t, List<T> list) {
        for (T a : list) {
            if (a.getName().equals(t.getName())) {
                return true;
            }
        }
        return false;
    }

    private static boolean compareArguments(String ifName, Iterator<FArgument> iter_aarg, Iterator<FArgument> iter_barg) {
        boolean result = true;

        while (iter_aarg.hasNext() && iter_barg.hasNext()) {
            FArgument aarg = iter_aarg.next();
            FArgument barg = iter_barg.next();
            if (!barg.getName().equals(aarg.getName())) {
                System.out.println(ifName + " input argument " + aarg.getName() + " renamed to " + barg.getName());
                result = false;
                continue;
            }
            if (aarg.getType().getPredefined() != barg.getType().getPredefined()) {
                System.out.println(ifName + " input argument " + aarg.getName() + " changed type");
                result = false;
            }
        }

        return result;
    }
}
