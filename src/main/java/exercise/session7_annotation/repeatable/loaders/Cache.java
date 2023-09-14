package exercise.session7_annotation.repeatable.loaders;

import static exercise.session7_annotation.repeatable.annotations.Annotations.*;

@ScheduledExecutorClass
public class Cache {

    @ExecuteOnSchedule (periodSeconds = 1)
    @ExecuteOnSchedule (delaySeconds = 1, periodSeconds = 1)
    public static void reloadCache () {
        System.out.println ("Reloading the cache");
    }
}
