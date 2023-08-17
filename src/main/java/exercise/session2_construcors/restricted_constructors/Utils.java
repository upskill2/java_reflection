package exercise.session2_construcors.restricted_constructors;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Utils {
    public static void initConfiguration () throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<ServerConfiguration> constructor = ServerConfiguration.class.getDeclaredConstructor (int.class, String.class);

        constructor.setAccessible (true);
        constructor.newInstance (8080, "Hello World");

    }
}