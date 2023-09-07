package exercise.session6_modifiers.auction;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Utils {


    public static void printClassModifiers (Class<?> clazz) {

        System.out.println (String.format ("Class %s access modifier is %s",
                clazz.getSimpleName (),
                getAccessModifierName (clazz.getModifiers ())));

        if (Modifier.isAbstract (clazz.getModifiers ())) {
            System.out.println (String.format ("Class %s is abstract",
                    clazz.getSimpleName ()));
        }

        if (Modifier.isStatic (clazz.getModifiers ())) {
            System.out.println (String.format ("Class %s is static",
                    clazz.getSimpleName ()));
        }

        if (Modifier.isInterface (clazz.getModifiers ())) {
            System.out.println (String.format ("Class %s is interface",
                    clazz.getSimpleName ()));
        }


    }

    public static void printFieldsAccessModifiers (Class<?> clazz) {

        Field[] fields = clazz.getDeclaredFields ();

        for (Field field : fields) {
            int modifier = field.getModifiers ();

            System.out.println (String.format ("Field %s access modifier is %s",
                    field.getName (),
                    getAccessModifierName (modifier)));

            if (Modifier.isSynchronized (modifier)) {
                System.out.println (String.format ("Field %s is synchronized",
                        field.getName ()));
            } else {
                System.out.println (String.format ("Field %s is not synchronized",
                        field.getName ()));
            }
            if (Modifier.isTransient (modifier)) {
                System.out.println (String.format ("Field %s is transient and will not be serialized",
                        field.getName ()));
            }
            if (Modifier.isFinal (modifier)) {
                System.out.println (String.format ("Field %s is final",
                        field.getName ()));
            }

        }

    }


    public static void printMethodModifiers (Class<?> clazz) {

        Method[] methods = clazz.getDeclaredMethods ();

        for (Method method : methods) {
            System.out.println (String.format ("Method %s access modifier is %s",
                    method.getName (),
                    getAccessModifierName (method.getModifiers ())));

            if (Modifier.isSynchronized (method.getModifiers ())) {
                System.out.println (String.format ("Method %s is synchronized",
                        method.getName ()));
            } else {
                System.out.println (String.format ("Method %s is not synchronized",
                        method.getName ()));
            }
            System.out.println ();
        }


    }


    public static String getAccessModifierName (int modifier) {
        if (Modifier.isPublic (modifier)) {
            return "public";
        } else if (Modifier.isPrivate (modifier)) {
            return "private";
        } else if (Modifier.isProtected (modifier)) {
            return "protected";
        }
        return "package-private";
    }

}
