package exercise.session3_class_fields.arrays;

import java.lang.reflect.Array;

public class Main {

    public static void main (String[] args) {

        int[] oneDimensinalArray = {12, 3, 4};
        double[][] twoDimensionalArray = {{1.4, 2.4}, {3.4, 4.5}};

        inspectArrayObject (twoDimensionalArray);
        System.out.println ();
        System.out.println ();

        inspectArray (oneDimensinalArray);

    }

    public static void inspectArray (Object arrayObject) {
        int arrayLength = Array.getLength (arrayObject);

        System.out.print ("[");

        for (int i = 0; i < arrayLength; i++) {
            Object element = Array.get (arrayObject, i);

            if (element.getClass ().isArray ()) {
                inspectArray (element);
            } else {

                System.out.print (element);
            }

            if (i != arrayLength - 1) {
                System.out.print (", ");

            }
        }
        System.out.print ("]");

    }


    public static void inspectArrayObject (Object arrayObject) {

        Class<?> clazz = arrayObject.getClass ();
        System.out.println (String.format ("Is array : %s", clazz.isArray ()));


        Class<?> arrayComponentType = clazz.getComponentType ();
        System.out.println (String.format ("Array component type : %s", arrayComponentType.getTypeName ()));

    }
}
