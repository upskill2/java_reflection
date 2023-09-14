package exercise.session7_annotation.discovery.utils;


import exercise.session7_annotation.discovery.Main;
import exercise.session7_annotation.discovery.annotations.InitializerClass;
import exercise.session7_annotation.discovery.annotations.InitializerMethod;
import exercise.session7_annotation.discovery.annotations.RetryOperation;
import exercise.session7_annotation.discovery.annotations.ScanPackages;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Utils {

    public static void initialize () throws Throwable {
        ScanPackages scanPackages = Main.class.getAnnotation (ScanPackages.class);
        if (scanPackages == null || scanPackages.packages ().length == 0) {
            return;
        }

        List<Class<?>> classes = getAllClasses (scanPackages.packages ());

        for (Class<?> clazz : classes) {

            if (!clazz.isAnnotationPresent (InitializerClass.class)) {
                continue;
            }
            Object instance = clazz.getDeclaredConstructor ().newInstance ();
            List<Method> methods = getAllInitializedMethods (clazz);

            for (Method method : methods) {
                callInitializingMethod (instance, method);
            }
        }

    }

    private static void callInitializingMethod (final Object instance, final Method method) throws Throwable {
        RetryOperation retryOperation = method.getAnnotation (RetryOperation.class);

        int numberOfRetries = retryOperation == null ? 0 : retryOperation.numberOfRetries ();

        while (true) {
            try {
                method.invoke (instance);
                break;
            } catch (InvocationTargetException e) {
                Throwable targetException = e.getTargetException ();

                if (numberOfRetries > 0 && Set.of (retryOperation.retryExceptions ()).contains (targetException.getClass ())) {
                    numberOfRetries--;
                    System.out.println ("Retrying ......");
                    Thread.sleep (retryOperation.durationBetweenRetriesMs ());
                } else if (retryOperation != null) {
                    throw new Exception (retryOperation.failureMessage (), targetException);
                } else {
                   throw targetException;
                }


            }


        }


    }

    public static List<Class<?>> getAllClasses (final String... packageNames) throws URISyntaxException, IOException {

        List<Class<?>> allClasses = new ArrayList<> ();

        for (String packageName : packageNames) {
            String packageRelativePath = packageName.replace (".", "/");

       //     URI packageURI = Utils.class.getResource ("/" + packageRelativePath).toURI ();
            URI packageURI = exercise.session7_annotation.repeatable.utils.Utils.class.getResource ("/" + packageRelativePath).toURI ();

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
