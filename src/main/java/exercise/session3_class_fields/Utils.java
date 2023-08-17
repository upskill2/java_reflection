package exercise.session3_class_fields;

public class Utils {

    public static void printDeclaredFields (Class<?> clazz) {
        for (var field : clazz.getDeclaredFields ()) {
            System.out.println (String.format ("Field name: %s, type: %s",
                    field.getName (),
                    field.getType ().getName ()));
            System.out.println ();
        }
    }
}
