package exercise.session3_class_fields.json_serializer.jsonwriter;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Utils {


    public static String objectToJson (Object instance, int indentSize) throws IllegalAccessException {

        Field[] fields = instance.getClass ().getDeclaredFields ();
        StringBuilder sb = new StringBuilder ();

        sb.append (indent (indentSize));
        sb.append ("{");
        sb.append ("\n");

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible (true);
            if (field.isSynthetic ()) {
                continue;
            }
            sb.append (indent (indentSize + 1));
            sb.append (formatString (field.getName ()));
            sb.append (":");

            if (field.getType ().isPrimitive ()) {
                sb.append (formatPrimitiveValue (field.get (instance), field.getType ()));
            } else if (field.getType ().equals (String.class)) {
                sb.append (formatString (field.get (instance).toString ()));
            } else if (field.getType ().isArray ()) {
                sb.append (arrayToJson (field.get (instance), indentSize + 1));
            } else {
                sb.append (objectToJson (field.get (instance), indentSize + 1));
            }

            if (i != fields.length - 1) {
                sb.append (",");
            }
            sb.append ("\n");
        }

        sb.append (indent (indentSize));
        sb.append ("}");
        return sb.toString ();
    }

    private static String arrayToJson (final Object arrayInstance, int indentSize) throws IllegalAccessException {

        int arrayLength = Array.getLength (arrayInstance);
        StringBuilder sb = new StringBuilder ();
        Class<?> componentType = arrayInstance.getClass ().getComponentType ();
        sb.append ("[");
        sb.append ("\n");

        for (int i = 0; i < arrayLength; i++) {
            Object element = Array.get (arrayInstance, i);

            if (componentType.isPrimitive ()) {
                sb.append (indent (indentSize + 1));
                sb.append (formatPrimitiveValue (element, componentType));
            } else if (componentType.equals (String.class)) {
                sb.append (indent (indentSize + 1));
                sb.append (formatString (element.toString ()));
            } else {
                sb.append (objectToJson (element, indentSize + 1));
            }


            if (i != arrayLength - 1) {
                sb.append (",");
            }
            sb.append ("\n");
        }

        sb.append (indent (indentSize));
        sb.append ("]");
        return sb.toString ();

    }

    private static String indent (int indentSize) {
        return IntStream.range (0, indentSize).mapToObj (i -> "\t").collect (Collectors.joining ());
    }

    private static String formatString (String s) {
        return String.format ("\"%s\"", s);
    }

    private static String formatPrimitiveValue (Object instance, Class<?> type) throws IllegalAccessException {
        if (type.equals (boolean.class)
                || type.equals (int.class)
                || type.equals (long.class)
                || type.equals (short.class)) {
            return String.format ("%s", instance);
        } else if (type.equals (double.class) || type.equals (float.class)) {
            return String.format ("%.2f", instance);
        }
        throw new RuntimeException (String.format ("Type %s not supported", type.getName ()));

    }


}




