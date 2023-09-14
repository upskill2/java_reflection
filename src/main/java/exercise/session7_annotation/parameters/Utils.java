package exercise.session7_annotation.parameters;

import static exercise.session7_annotation.parameters.annotations.Annotations.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class Utils {

    public static <T> T execute (Object instance) throws InvocationTargetException, IllegalAccessException {
        Class<?> clazz = instance.getClass ();

        Map<String, Method> operationToMethod = getOperationToMethodMap (clazz);
        Method finalResultMethod = findFinalResultMethod (clazz);

        return (T) executeWithDependencies (instance, operationToMethod, finalResultMethod);
    }

    private static Object executeWithDependencies (final Object instance, final Map<String, Method> operationToMethod, final Method finalResultMethod) throws InvocationTargetException, IllegalAccessException {
        List<Object> parameterValues = new ArrayList<> (finalResultMethod.getParameterCount ());

        for (Parameter parameter : finalResultMethod.getParameters ()) {
            Object value = null;
            if (parameter.isAnnotationPresent (DependsOn.class)) {
                String operationName = parameter.getAnnotation (DependsOn.class).value ();
                Method method = operationToMethod.get (operationName);

                value = executeWithDependencies (instance, operationToMethod, method);
            }

            parameterValues.add (value);
        }

        return finalResultMethod.invoke (instance, parameterValues.toArray ());
    }

    private static Map<String, Method> getOperationToMethodMap (Class<?> clazz) {

        Operation operation = clazz.getAnnotation (Operation.class);
        return Arrays.stream (clazz.getDeclaredMethods ())
                .filter (method -> method.isAnnotationPresent (Operation.class))
                .collect (HashMap::new, (map, method) -> map.put (method.getAnnotation (Operation.class).value (), method), HashMap::putAll);
    }

    private static Method findFinalResultMethod (Class<?> clazz) {
        return Arrays.stream (clazz.getDeclaredMethods ())
                .filter (method -> method.isAnnotationPresent (FinalResult.class))
                .findFirst ()
                .orElseThrow (() -> new RuntimeException ("No method found with @FinalResult annotation"));
    }
}
