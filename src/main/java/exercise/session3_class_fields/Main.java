package exercise.session3_class_fields;

import java.lang.reflect.Field;

import static exercise.session3_class_fields.Utils.printDeclaredFields;

public class Main {

    public static void main (String[] args) throws ClassNotFoundException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
        //printDeclaredFields (Class.forName ("exercise.session3_class_fields.Movie$MovieStats"));
        //  printDeclaredFields (Category.class);
        Movie movie = new Movie ("The Lord of the Rings", 2001, true, Category.ADVENTURE, 12.99);

        printDeclaredFields (Movie.class, movie);

        Field minPriceStaticField = Movie.class.getDeclaredField ("MINIMUM_PRICE");

        System.out.println (String.format ("static MINIMUM_PRICE value :%f", minPriceStaticField.get (null)));;
    }
}
