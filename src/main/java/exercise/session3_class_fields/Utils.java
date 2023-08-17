package exercise.session3_class_fields;

import exercise.session2_construcors.restricted_constructors.ServerConfiguration;

import java.lang.reflect.Constructor;

public class Utils {

    public static <T> void printDeclaredFields (Class<? extends T> clazz, T instance) throws IllegalAccessException, NoSuchMethodException {

        Constructor<?>[] constructor = clazz.getDeclaredConstructors ();
        constructor[0].setAccessible (true);

        for (var field : clazz.getDeclaredFields ()) {
            Constructor constructor1 = clazz.getDeclaredConstructors ()[0];
            constructor1.setAccessible (true);
            System.out.println (String.format ("Field name: %s, type: %s",
                    field.getName (),
                    field.getType ().getName ()));

            System.out.println (String.format ("Is synthetic field : %s", field.isSynthetic ()));
            System.out.println (String.format ("Field value is : %s", field.get (instance)));
            System.out.println ();
        }
    }
}
