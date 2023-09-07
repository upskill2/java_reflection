package exercise.session5_methods.discovery.polymorphism;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    public static Map<Object, Method> groupExecutors (List<Object> requestExecutors, List<Class<?>> methodParameterTypes) {
        HashMap<Object, Method> executorToMethod = new HashMap<> ();

        for (Object requestExecutor : requestExecutors) {
            List<Method> methods = List.of (requestExecutor.getClass ().getDeclaredMethods ());
            for (Method method : methods) {
                if (Arrays.asList (method.getParameterTypes ()).equals (methodParameterTypes)) {
                    executorToMethod.put (requestExecutor, method);
                }
            }
        }
        return executorToMethod;
    }

    public static void executeAll (Map<Object, Method> requestExecutor, String requestBody) throws Throwable {


        try {
            for (Map.Entry<Object, Method> entry : requestExecutor.entrySet ()) {
                Object requestExecutorObject = entry.getKey ();
                Method requestExecutorMethod = entry.getValue ();

                Boolean result = (Boolean) requestExecutorMethod.invoke (requestExecutorObject, requestBody);

                if (result != null && result.equals (Boolean.FALSE)) {
                    System.out.println (String.format ("Request : %s was not sent. Aborting the execution", requestBody));
                    return;
                }

            }
        } catch (InvocationTargetException e) {
            throw e.getTargetException ();
        }

    }
}
