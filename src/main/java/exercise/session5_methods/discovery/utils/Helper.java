package exercise.session5_methods.discovery.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class Helper {

    public static List<Field> getAllFields (Class<?> dataClass) {

        if (dataClass == null || dataClass.getClass ().equals (Object.class)) {
            return Collections.emptyList ();
        }

        List<Field> fields = new ArrayList<> ();

        fields.addAll (Arrays.asList (dataClass.getDeclaredFields ()));

        if (dataClass.getSuperclass () != null) {
            fields.addAll (getAllFields (dataClass.getSuperclass ()));
        }

        return fields;
    }


    public static void testSetters (Class<?> declaredClass) {

        List<Field> fields = getAllFields (declaredClass);

        for (Field field : fields) {
            String setterName = "set" + capitalizeFirstLetter (field.getName ());

            Method setterMethod = null;

            try {
                setterMethod = declaredClass.getMethod (setterName, field.getType ());
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException (String.format ("Setter : %s not fount", setterName));
            }

            if (!setterMethod.getReturnType ().equals (void.class)) {
                throw new IllegalArgumentException (String.format ("Setter : %s has return type %s but expected void",
                        setterName,
                        setterMethod.getReturnType ().getTypeName ()));
            }

        }


    }

    public static void testGetters (Class<?> dataClass) {

        List<Field> fields = getAllFields (dataClass);
        Map<String, Method> methodNameToMethod = mapMethodNameToMethod (dataClass);

        for (Field field : fields) {
            String getterName = "get" + capitalizeFirstLetter (field.getName ());

            if (!methodNameToMethod.containsKey (getterName)) {
                throw new IllegalStateException (String.format ("Field: %s doesn't have a getter method", field.getName ()));
            }


            Method getter = methodNameToMethod.get (getterName);

            if (!getter.getReturnType ().equals (field.getType ())) {
                throw new IllegalStateException (String.format ("Getter method : %s has return type %s but expected %s",
                        getterName,
                        getter.getReturnType ().getTypeName (),
                        field.getType ().getTypeName ()));
            }

            if (getter.getParameterCount () > 0) {
                throw new IllegalStateException (String.format ("Getter method : %s has %d parameters but expected 0",
                        getterName,
                        getter.getParameterCount ()));

            }

        }
    }

    private static String capitalizeFirstLetter (final String name) {

        return name.substring (0, 1).toUpperCase () + name.substring (1);
    }

    private static Map<String, Method> mapMethodNameToMethod (Class<?> dataClass) {

        Method[] methods = dataClass.getMethods ();
        Map<String, Method> methodNameToMethod = new HashMap<> ();

        for (Method method : methods) {
            methodNameToMethod.put (method.getName (), method);

        }

        return methodNameToMethod;
    }
}
