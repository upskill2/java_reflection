package exercise.session4_fields_modification.utils;

import exercise.session1_classes.ClassAnalyzer;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Scanner;

public class ConfigParser {

    public static <T> T createConfigObject (Class<T> clazz, Path filePath) throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        Scanner scanner = new Scanner (filePath);
        Constructor<?> constructor = clazz.getDeclaredConstructor ();
        constructor.setAccessible (true);

        T configInstance = (T) constructor.newInstance ();

        while (scanner.hasNextLine ()) {
            String configLine = scanner.nextLine ();

            String[] nameValuePair = configLine.split ("=");
            String propertyName = nameValuePair[0];
            String propertyValue = nameValuePair[1];

            Field field;
            try {
                field = clazz.getDeclaredField (propertyName);
            } catch (NoSuchFieldException e) {
                System.out.println (String.format ("Property name: %s is not supported", propertyName));
                continue;
            }

            field.setAccessible (true);
            Object parsedValue;
            if(field.getType ().isArray ()){
             parsedValue =   parseArray (field.getType ().getComponentType (), propertyValue);
            } else {
                parsedValue = parseValue (field.getType (), propertyValue);
            }

            field.set (configInstance, parsedValue);
        }


        return configInstance;
    }

    public static Object parseArray (Class<?> arrayElementType, String value) {

        String[] values = value.split (",");
        Object array = Array.newInstance (arrayElementType, values.length);
        for (int i = 0; i < values.length; i++) {
            Array.set (array, i, parseValue (arrayElementType, values[i]));
        }
        return array;

    }

    private static Object parseValue (final Class<?> type, final String propertyValue) {

        if (type == int.class) {
            return Integer.parseInt (propertyValue);
        } else if (type == double.class) {
            return Double.parseDouble (propertyValue);
        } else if (type == String.class) {
            return propertyValue;
        } else if (type == boolean.class) {
            return Boolean.parseBoolean (propertyValue);
        } else if (type == long.class) {
            return Long.parseLong (propertyValue);
        } else if (type == float.class) {
            return Float.parseFloat (propertyValue);
        } else if (type == short.class) {
            return Short.parseShort (propertyValue);
        } else if (type == byte.class) {
            return Byte.parseByte (propertyValue);
        } else if (type.equals (String.class)) {
            return propertyValue;
        }


        return new RuntimeException (String.format ("Type: %s is not supported", type.getSimpleName ()));
    }
}
