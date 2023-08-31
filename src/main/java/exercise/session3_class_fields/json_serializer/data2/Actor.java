package exercise.session3_class_fields.json_serializer.data2;

public class Actor {

    private final String name;
    private final String[] knownMovies;

    public Actor (String name, String[] knownMovies) {
        this.name = name;
        this.knownMovies = knownMovies;
    }
}
