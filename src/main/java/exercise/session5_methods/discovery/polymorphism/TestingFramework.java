package exercise.session5_methods.discovery.polymorphism;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestingFramework {

    public void runTestSuite (Class<?> testClass) throws Throwable {
        Method[] methods = testClass.getMethods ();
        Object testObject = testClass.getDeclaredConstructor ().newInstance ();


        Method beforeAllMethod = findMethodByName (methods, "beforeClass");
        if (beforeAllMethod != null && beforeAllMethod.getParameterCount () == 0) {
            beforeAllMethod.invoke (null);
        }

        Method setupMethod = findMethodByName (methods, "setupTest");
/*        if (setupMethod != null && setupMethod.getReturnType ().equals (void.class) && setupMethod.getParameterCount () == 0) {

        }*/

        List<Method> testMethods = findMethodsByPrefix (methods, "test");
        for (Method testMethod : testMethods) {
            if (setupMethod == null || testMethod.getParameterCount () != 0 || !testMethod.getReturnType ().equals (void.class)) {
                continue;
            }
            setupMethod.invoke (testObject);
            testMethod.invoke (testObject);
        }

        Method afterMethod = findMethodByName (methods, "afterClass");
        if (afterMethod != null) {
            afterMethod.invoke (null);
        }


        /**
         * Complete your code here
         */
    }

    /**
     * Helper method to find a method by name
     * Returns null if a method with the given name does not exist
     */
    private Method findMethodByName (Method[] methods, String name) {
        for (Method method : methods) {
            if (method.getName ().equals (name)) {
                return method;
            }

        }
        return null;
    }

    /**
     * Helper method to find all the methods that start with the given prefix
     */
    private List<Method> findMethodsByPrefix (Method[] methods, String prefix) {

        return Arrays.stream (methods)
                .filter (method -> method.getName ().startsWith (prefix))
                .collect (Collectors.toList ());

        /**
         * Complete your code here
         */
    }
}
