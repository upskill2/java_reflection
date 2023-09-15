package exercise.session8_proxies.external;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TimeMeasuringProxyHandler implements InvocationHandler {
    private final Object originalObject;

    public TimeMeasuringProxyHandler (Object originalObject) {
        this.originalObject = originalObject;
    }

    @Override
    public Object invoke (final Object proxy, final Method method, final Object[] args) throws Throwable {
        Object result;
        System.out.println (String.format ("Measuring Proxy - before executing the method :%s()", method.getName ()));

        long currentTime = System.currentTimeMillis ();


        try {
            result = method.invoke (originalObject, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException ();
        }
        long executionTime = System.currentTimeMillis ();

        System.out.println ();
        System.out.println (String.format ("Measuring Proxy - Execution of %s() took %dms", method.getName (), executionTime - currentTime));

        return result;
    }
}
