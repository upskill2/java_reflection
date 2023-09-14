package exercise.session7_annotation.discovery;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Exercise {

    /**
     * Complete your code here if necessary
     */
    @Target (ElementType.METHOD)
    @Retention (RetentionPolicy.RUNTIME)
    public @interface OpenResources {
        /**
         * Complete your code here if necessary
         */
    }

    public Set<Method> getAllAnnotatedMethods (Object input) {
        Set<Method> annotatedMethods = new HashSet<> ();

        Class<?> clazz = input.getClass ();
        List<Method> methods = Arrays.asList (clazz.getDeclaredMethods ());

        for (Method method : methods) {
            if (method.isAnnotationPresent (OpenResources.class)) {
                annotatedMethods.add (method);
            }

        }
        /**
         * Complete your code here
         */
        return annotatedMethods;
    }

}
