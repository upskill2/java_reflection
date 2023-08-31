package exercise.session3_class_fields.json_serializer.jsonwriter;

import exercise.session3_class_fields.json_serializer.data2.Actor;
import exercise.session3_class_fields.json_serializer.data2.Movie;

public class Main2 {

    public static void main (String[] args) throws IllegalAccessException {
        Actor actor1 = new Actor ("John", new String[]{"Movie1", "Movie2", "Movie3"});
        Actor actor2 = new Actor ("John2", new String[]{"Movie1", "Movie2", "Movie3"});
        Actor actor3 = new Actor ("John3", new String[]{"Movie1", "Movie2", "Movie3"});


        Movie movie = new Movie ("Movie1", 8.5f, new String[]{"Actor1", "Actor2", "Actor3"}, new Actor[]{actor1, actor2, actor3});

        String json = Utils.objectToJson (movie, 0);

        System.out.println (json);

    }
}
