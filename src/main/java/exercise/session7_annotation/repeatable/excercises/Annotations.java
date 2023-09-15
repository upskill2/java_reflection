package exercise.session7_annotation.repeatable.excercises;

import java.lang.annotation.*;

public class Annotations {

    /**
     * COMPLETE YOUR CODE HERE
     */
    @Repeatable (PermissionsContainer.class)
    @Target (ElementType.TYPE)
    public @interface Permissions {
       // Role role ();
     //   OperationType[] allowed ();
        /**
         * COMPLETE YOUR CODE HERE
         */
    }

    @Retention (RetentionPolicy.RUNTIME)
    @Target (ElementType.TYPE)
    public @interface PermissionsContainer {
        Permissions[] value();
        }

}