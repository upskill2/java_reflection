package exercise.session7_annotation.discovery.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention (RetentionPolicy.RUNTIME)
@Target ({ElementType.TYPE})
public @interface ScanPackages {

    String[] packages () default {"app"};
}
