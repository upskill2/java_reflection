package exercise.session3_class_fields.objectsize;

import java.lang.reflect.*;
import java.util.logging.Logger;

public class ObjectSizeCalculator {

    private final static Logger LOGGER = Logger.getLogger (ObjectSizeCalculator.class.getName ());
    private static final long HEADER_SIZE = 12;
    private static final long REFERENCE_SIZE = 4;

    public long sizeOfObject (Object input) throws IllegalAccessException {

        Field[] fields = input.getClass ().getDeclaredFields ();

        long result = HEADER_SIZE + REFERENCE_SIZE;

        for (Field field : fields) {
            field.setAccessible (true);
            if (field.getType ().equals (String.class)) {
                LOGGER.info (String.format ("Field name: %s, type: %s",
                        field.getName (),
                        field.getType ().getName ()));
                result += sizeOfString (field.get (input).toString ());
                LOGGER.info (String.format ("String Field name : %s size is: %s ", field.getName (), sizeOfString (field.getName ())));
            } else if (field.getType ().isPrimitive ()) {
                result += sizeOfPrimitiveType (field.getType ());
                LOGGER.info (String.format ("Primitive Field name : %s size is: %s ", field.getName (), sizeOfString (field.getName ())));
            }
        }

        return result;


    }


    /*************** Helper methods ********************************/
    private long sizeOfPrimitiveType (Class<?> primitiveType) {
        if (primitiveType.equals (int.class)) {
            return 4;
        } else if (primitiveType.equals (long.class)) {
            return 8;
        } else if (primitiveType.equals (float.class)) {
            return 4;
        } else if (primitiveType.equals (double.class)) {
            return 8;
        } else if (primitiveType.equals (byte.class)) {
            return 1;
        } else if (primitiveType.equals (short.class)) {
            return 2;
        }
        throw new IllegalArgumentException (String.format ("Type: %s is not supported", primitiveType));
    }

    private long sizeOfString (String inputString) {
        int stringBytesSize = inputString.getBytes ().length;
        return HEADER_SIZE + REFERENCE_SIZE + stringBytesSize;
    }
}