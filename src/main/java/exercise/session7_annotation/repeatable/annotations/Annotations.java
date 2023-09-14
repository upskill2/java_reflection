package exercise.session7_annotation.repeatable.annotations;

import java.lang.annotation.*;

public class Annotations {


    @Retention (RetentionPolicy.RUNTIME)
    @Target (ElementType.TYPE)
    public @interface ScheduledExecutorClass {
    }


    @Repeatable (ScheduledExecutors.class)
    @Target (ElementType.METHOD)
    public @interface ExecuteOnSchedule {
        int delaySeconds () default 10;

        int periodSeconds () default 10;
    }

    @Retention (RetentionPolicy.RUNTIME)
    @Target (ElementType.METHOD)
    public @interface ScheduledExecutors {
        ExecuteOnSchedule[] value ();
    }

}
