package exercise.session8_proxies.utils;

import exercise.session8_proxies.external.DatabaseReader;
import exercise.session8_proxies.external.HttpClient;
import exercise.session8_proxies.external.TimeMeasuringProxyHandler;

import java.io.IOException;
import java.lang.reflect.Proxy;

public class Utils {

    public static void useHttpClient (HttpClient httpClient) {
        httpClient.initialize ();
        String response = httpClient.sendRequest ("someRequest");

        System.out.println (String.format ("Http rsponse is :%s", response));
    }

    public static void useDatabaseReader (DatabaseReader databaseReader) throws InterruptedException, IOException {
        //   throw new IOException ("Database is not available");
        int count = 0;
        try {
            count = databaseReader.countRowsInTable ("GamesTable");
        } catch (IOException e) {
            System.out.println ("Catching IO exception" + e);
        }
        System.out.println (String.format ("There are :%d rows in the GamesTable", count));


        String[] row = databaseReader.readRow ("SELECT * FROM GamesTable WHERE id = 1");
        System.out.println (String.format ("Received %s", String.join (",", row)));
    }

    @SuppressWarnings ("unchecked")
    public static <T> T createProxy (Object originalObject) {
        Class<?>[] interfaces = originalObject.getClass ().getInterfaces ();
        TimeMeasuringProxyHandler proxyHandler = new TimeMeasuringProxyHandler (originalObject);

        return (T) Proxy.newProxyInstance (originalObject.getClass ().getClassLoader (), interfaces, proxyHandler);
    }

}
