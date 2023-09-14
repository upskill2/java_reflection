package exercise.session7_annotation.repeatable.utils;

import exercise.session7_annotation.discovery.annotations.ScanPackages;
import exercise.session7_annotation.repeatable.Main;
import exercise.session7_annotation.repeatable.annotations.Annotations;

import static exercise.session7_annotation.repeatable.annotations.Annotations.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static exercise.session7_annotation.discovery.utils.Utils.getAllClasses;


public class Utils {

    public static void schedule () throws URISyntaxException, IOException {
        ScanPackages scanPackages = Main.class.getAnnotation (ScanPackages.class);

        if (scanPackages == null || scanPackages.packages ().length == 0) {
            System.out.println ("No packages to scan");
            return;
        }

        List<Class<?>> allClasses = getAllClasses (scanPackages.packages ());
        List<Method> scheduledExecutorMethods = getScheduledExecutorMethods (allClasses);

        for (Method method : scheduledExecutorMethods) {
            scheduleMethodExecution (method);
        }
    }

    private static void scheduleMethodExecution (final Method method) {
        ExecuteOnSchedule[] schedules = method.getAnnotationsByType (ExecuteOnSchedule.class);

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor ();

        for (ExecuteOnSchedule schedule : schedules) {
            scheduledExecutorService.scheduleAtFixedRate (
                    () -> runWhenScheduled (method),
                    schedule.delaySeconds (),
                    schedule.periodSeconds (),
                    TimeUnit.SECONDS);

        }


    }

    private static void runWhenScheduled (final Method method) {
        Date currentDate = new Date ();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("HH:mm:ss");

        System.out.println (String.format ("Executing method %s at %s", method.getName (), simpleDateFormat.format (currentDate)));

        try {
            method.invoke (null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace ();
        }
    }


    private static List<Method> getScheduledExecutorMethods (List<Class<?>> allClasses) {

        List<Method> scheduledMethods = new ArrayList<> ();

        for (Class<?> clazz : allClasses) {
            if (!clazz.isAnnotationPresent (ScheduledExecutorClass.class)) {
                continue;
            }
            for (Method method : clazz.getDeclaredMethods ()) {
                if (method.getAnnotationsByType (ExecuteOnSchedule.class).length > 0) {
                    scheduledMethods.add (method);
                }
            }
        }
        //   return scheduledMethods;
        return allClasses
                .stream ()
                .filter (clazz -> clazz.isAnnotationPresent (Annotations.ScheduledExecutorClass.class))
                .flatMap (clazz -> Arrays.stream (clazz.getDeclaredMethods ())
                        .filter (method -> method.getAnnotationsByType (ExecuteOnSchedule.class).length > 0))
                .collect (Collectors.toList ());


    }


}
