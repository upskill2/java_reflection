package exercise.session7_annotation.discovery.utils;


import exercise.session7_annotation.discovery.annotations.InitializerClass;
import exercise.session7_annotation.discovery.annotations.InitializerMethod;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utils {

    public static void initialize (String... packages) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, URISyntaxException, IOException {
        List<Class<?>> classes = getAllClasses (packages);

        for (Class<?> clazz : classes) {

            if (!clazz.isAnnotationPresent (InitializerClass.class)) {
                continue;
            }
            Object instance = clazz.getDeclaredConstructor ().newInstance ();
            List<Method> methods = getAllInitializedMethods (clazz);

            for (Method method : methods) {
                method.invoke (instance);
            }
        }

    }

    private static List<Class<?>> getAllClasses (final String... packageNames) throws URISyntaxException, IOException {

        List<Class<?>> allClasses = new ArrayList<> ();

        for (String packageName : packageNames) {
            String packageRelativePath = packageName.replace (".", "/");

            URI packageURI = Utils.class.getResource ("/" + packageRelativePath).toURI ();

            if ("file".equals (packageURI.getScheme ())) {
                Path packageFullPath = Paths.get (packageURI);
                allClasses.addAll (getAllPackagesClasses (packageFullPath, packageName));
            } else if ("jar".equals (packageURI.getScheme ())) {
                FileSystem fileSystem = FileSystems.newFileSystem (packageURI, Collections.emptyMap ());
                Path packageFullPath = fileSystem.getPath (packageRelativePath);
                allClasses.addAll (getAllPackagesClasses (packageFullPath, packageName));

                fileSystem.close ();
            }
        }
        return allClasses;
    }


    private static List<Class<?>> getAllPackagesClasses (Path packagePath, String packageName) throws IOException {

        if (!Files.exists (packagePath)) {
            return Collections.emptyList ();
        }

        List<Path> files = Files.list (packagePath)
                .filter (f -> Files.isRegularFile (f))
                .toList ();

        List<Class<?>> classes = new ArrayList<> ();


        for (Path file : files) {
            String fileName = file.getFileName ().toString ();

            if (fileName.endsWith (".class")) {
                try {
                    String classFullName = packageName + "." + fileName.replace (".class", "");
                    Class<?> clazz = Class.forName (classFullName);
                    classes.add (clazz);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace ();
                }

            }
        }


        return classes;

    }

    private static List<Method> getAllInitializedMethods (Class<?> clazz) {
        List<Method> initializedMethods = new ArrayList<> ();
        for (Method method : clazz.getDeclaredMethods ()) {
            if (method.isAnnotationPresent (InitializerMethod.class)) {
                initializedMethods.add (method);
            }
        }
        return initializedMethods;
    }
}
