package exercise.session4_fields_modification.data;

import java.util.Arrays;
import java.util.Random;

public class GameConfig {
    //private final static int releaseYear = 2000;
    private int releaseYear;
    private String gameName;
    private double price;
    private String[] characterNames;

    public String[] getCharacterNames () {
        return characterNames;
    }

/*    public GameConfig () {
        this.releaseYear = new Random().nextInt (200);
    }*/

    public int getReleaseYear () {
        return releaseYear;
    }

    public String getGameName () {
        return gameName;
    }

    public double getPrice () {
        return price;
    }

    @Override
    public String toString () {
        return "GameConfig{" +
                "releaseYear=" + releaseYear +
                ", gameName='" + gameName + '\'' +
                ", price=" + price +
                ", characterNames=" + Arrays.toString (characterNames) +
                '}';
    }
}
