package exercise.session7_annotation.parameters;

import static exercise.session7_annotation.parameters.annotations.Annotations.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class Utils {

    public static <T> T execute (Object instance) throws InvocationTargetException, IllegalAccessException {
        Class<?> clazz = instance.getClass ();

        Map<String, Method> operationToMethod = getOperationToMethodMap (clazz);
        Map<String, Field> inputToFiled = getInputToFiled (clazz);
        Method finalResultMethod = findFinalResultMethod (clazz);


        return (T) executeWithDependencies (instance, operationToMethod, finalResultMethod, inputToFiled);
    }

    private static Map<String, Field> getInputToFiled (Class<?> clazz) {

        return Arrays.stream (clazz.getDeclaredFields ())
                .filter (method -> method.isAnnotationPresent (Input.class))
                .collect (HashMap::new, (map, field) -> map.put (field.getAnnotation (Input.class).value (), field), HashMap::putAll);
    }

    private static Object executeWithDependencies (final Object instance, final Map<String, Method> operationToMethod,
                                                   final Method finalResultMethod, final Map<String, Field> inputField) throws InvocationTargetException, IllegalAccessException {
        List<Object> parameterValues = new ArrayList<> (finalResultMethod.getParameterCount ());

        for (Parameter parameter : finalResultMethod.getParameters ()) {
            Object value = null;
            if (parameter.isAnnotationPresent (DependsOn.class)) {
                String operationName = parameter.getAnnotation (DependsOn.class).value ();
                Method method = operationToMethod.get (operationName);

                value = executeWithDependencies (instance, operationToMethod, method, inputField);
            } else if (parameter.isAnnotationPresent (Input.class)) {
                String fieldName = parameter.getAnnotation (Input.class).value ();
                Field field = inputField.get (fieldName);
                field.setAccessible (true);
                value = field.get (instance);

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
