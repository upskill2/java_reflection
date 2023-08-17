package exercise.session2_construcors.constructors_factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static void printConstructorData (Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getConstructors ();

        System.out.println (String.format ("class %s has %d declared constructors", clazz.getSimpleName (), constructors.length));
        for (int i = 0; i < constructors.length; i++) {
            Class<?>[] parameterTypes = constructors[i].getParameterTypes ();

            List<String> parameterTypeNames = Arrays.stream (parameterTypes)
                    .map (type -> type.getSimpleName ())
                    .collect (Collectors.toList ());

            System.out.println (parameterTypeNames);
        }
    }

    public static <T> T createInstanceWithArguments (Class<T> clazz, Object... arguments) throws InvocationTargetException, InstantiationException,
            IllegalAccessException {
        for (Constructor<?> constructor : clazz.getConstructors ()) {
            if (constructor.getParameterTypes ().length == arguments.length) {
                return (T) constructor.newInstance (arguments);
            }
        }
        System.out.println ("An appropriate constructor was not found");
        return null;
    }

}