package exercise.session3_class_fields.json_serializer.jsonwriter;

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
                sb.append (formatPrimitiveValue (field, instance));
            } else if (field.getType ().equals (String.class)) {
                sb.append (formatString (field.get (instance).toString ()));
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

    private static String indent (int indentSize) {
        return IntStream.range (0, indentSize).mapToObj (i -> "\t").collect (Collectors.joining ());
    }

    private static String formatString (String s) {
        return String.format ("\"%s\"", s);
    }

    private static String formatPrimitiveValue (Field field, Object instance) throws IllegalAccessException {
        if (field.getType ().equals (boolean.class)
                || field.getType ().equals (int.class)
                || field.getType ().equals (long.class)
                || field.getType ().equals (short.class)) {
            return String.format ("%s", field.get (instance));
        } else if (field.getType ().equals (double.class) || field.getType ().equals (float.class)) {
            return String.format ("%.2f", field.get (instance));
        }
        throw new RuntimeException (String.format ("Type %s not supported", field.getType ().getName ()));

    }


}




