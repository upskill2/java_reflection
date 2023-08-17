package exercise.session1_classes;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main (String[] args) throws ClassNotFoundException {

        //case 1
        Class<String> stringClass = String.class;

        Class<?> intClass = int.class;

        //case 2
        Map<String, Integer> mapObject = new HashMap<> ();
        Class<?> hasMapclass = mapObject.getClass ();

        //case 3
        Class<?> squareClass = Class.forName ("exercise.session1_classes.Square");

        //    printClassInfo (stringClass, hasMapclass, squareClass);

        var circleObject = new Drawable () {
            @Override
            public int getNumberOfCorners () {
                return 0;
            }
        };

        printClassInfo (intClass, Collection.class, boolean.class, int[][].class, Color.class, circleObject.getClass ());

    }

    public static void printClassInfo (Class<?>... classes) {
        for (Class<?> clazz : classes) {

            System.out.println (String.format ("Class name: %s, class package name: %s",
                    clazz.getSimpleName (),
                    clazz.getPackage ()));

            Class<?>[] implementedInterfaces = clazz.getInterfaces ();

            for (Class<?> implementedInterface : clazz.getInterfaces ()) {
                System.out.println (String.format ("Class: %s implements: %s"
                        , clazz.getSimpleName ()
                        , implementedInterface.getSimpleName ()));

            }
            System.out.println ("Superclass: " + clazz.getSuperclass ());
            String[] emptyArray = {""};


            String[] s = clazz.getSuperclass () == null ? emptyArray : new String[]{clazz.getSuperclass ().getCanonicalName ()};
            System.out.println ("Package name: " + clazz.getPackage ());
            System.out.println ("Superclass simpleName: " + s);
            System.out.println ("Is array: " + clazz.isArray ());
            System.out.println ("Is primitive: " + clazz.isPrimitive ());
            System.out.println ("Is interface: " + clazz.isInterface ());
            System.out.println ("Is enum: " + clazz.isEnum ());
            System.out.println ("Is anonymous: " + clazz.isAnonymousClass ());

            System.out.println ();
            System.out.println ();
        }
    }
}
