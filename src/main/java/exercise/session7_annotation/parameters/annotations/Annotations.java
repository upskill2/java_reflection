package exercise.session7_annotation.parameters.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Annotations {

    @Target (ElementType.METHOD)
    @Retention (RetentionPolicy.RUNTIME)
    public @interface Operation {
        String value ();
    }

    @Retention (RetentionPolicy.RUNTIME)
    @Target (ElementType.PARAMETER)
    public @interface DependsOn {
        String value ();
    }

    @Target (ElementType.METHOD)
    @Retention (RetentionPolicy.RUNTIME)
    public @interface FinalResult {

    }

}
