package exercise.session3_class_fields;

import static exercise.session3_class_fields.Utils.printDeclaredFields;

public class Main {

    public static void main (String[] args) throws ClassNotFoundException {
        printDeclaredFields (Class.forName ("exercise.session3_class_fields.Movie"));
    }
}
