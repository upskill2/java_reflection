package exercise.session4_fields_modification.configloader;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayFlattening {

    public <T> T concat (Class<?> type, Object... arguments) {

        if (arguments.length == 0) {
            return null;
        }

        int length = 0;
        for (Object argument : arguments) {
            if (argument.getClass ().isArray ()) {
                length += Array.getLength (argument);
            } else {
                length++;
            }
        }
        T array = (T) Array.newInstance (type, length);

        int index = 0;
        for (int i = 0; i < arguments.length; i++) {
            if (arguments[i].getClass ().isArray ()) {
                for (int j = 0; j < Array.getLength (arguments[i]); j++) {
                    {
                        Array.set (array, index, Array.get (arguments[i], j));
                        index++;

                    }
                }
            } else {
                Array.set (array, index, arguments[i]);
                index++;
            }


        }

        for (int i = 0; i < Array.getLength (array); i++) {
            System.out.print (Array.get (array, i));
            System.out.print (", ");
        }
        System.out.println ();
        return array;

    }

    public static void main (String[] args) {
        ArrayFlattening arrayFlattening = new ArrayFlattening ();
        arrayFlattening.concat (int.class, 1, 2, 3, new int[]{4, 5, 6}, 7);
        arrayFlattening.concat (String.class, new String[]{"a", "b"}, "c", new String[]{"d", "e"});
    }
}

