package exercise.session7_annotation.parameters;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static exercise.session7_annotation.parameters.Utils.execute;

public class Main {

    public static void main (String[] args) throws InvocationTargetException, IllegalAccessException {
        BestGameFinder bestGameFinder = new BestGameFinder ();

        List<String> bestGamesInDescendingOrder =  execute (bestGameFinder);

        System.out.println ("Best games in descending order: " + bestGamesInDescendingOrder);

    }
}
