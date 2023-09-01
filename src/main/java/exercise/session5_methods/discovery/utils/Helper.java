package exercise.session5_methods.discovery.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Helper {

    public static void testGetters (Class<?> dataClass) {

        Field[] fields = dataClass.getDeclaredFields ();
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
