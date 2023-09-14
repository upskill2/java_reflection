package exercise.session7_annotation.parameters;

import static exercise.session7_annotation.parameters.annotations.Annotations.*;

import exercise.session7_annotation.parameters.annotations.Annotations;
import exercise.session7_annotation.parameters.databases.Database;

import java.util.*;

public class BestGameFinder {

    private Database database = new Database ();

    @Operation ("All-Games")
    public Set<String> getAllGames () {
        return database.readAllGames ();
    }

    @Operation ("Game-To-Price")
    public Map<String, Float> getGameToPrice (@DependsOn ("All-Games") Set<String> allGames) {
        return database.readGameToPrice (allGames);
    }

    @Operation ("Game-To-Rating")
    public Map<String, Float> getGameToRating (@DependsOn ("All-Games") Set<String> allGames) {
        return database.readGameToRatings (allGames);
    }

    @Operation ("Score-To-Game")
    public SortedMap<Double, String> getGameToScore (@DependsOn ("Game-To-Price") Map<String, Float> getGameToPrice,
                                                     @DependsOn ("Game-To-Rating") Map<String, Float> getGameToRating) {

        SortedMap<Double, String> gameToScore = new TreeMap<> (Collections.reverseOrder ());

        for (String game : getGameToPrice.keySet ()) {
            double score = getGameToPrice.get (game) / getGameToRating.get (game);
            gameToScore.put (score, game);
        }

        return gameToScore;
    }

    @FinalResult
    public List<String> getBestGames (@DependsOn ("Score-To-Game") SortedMap<Double, String> gameToScore) {
        return new ArrayList<> (gameToScore.values ());
    }

}
