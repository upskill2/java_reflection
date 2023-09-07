package exercise.session5_methods.discovery.polymorphism;

import exercise.session5_methods.discovery.polymorphism.database.DatabaseClient;
import exercise.session5_methods.discovery.polymorphism.http.HttpClient;
import exercise.session5_methods.discovery.polymorphism.logging.FileLogger;
import exercise.session5_methods.discovery.polymorphism.udp.UdpClient;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import static exercise.session5_methods.discovery.polymorphism.Utils.executeAll;
import static exercise.session5_methods.discovery.polymorphism.Utils.groupExecutors;

public class Main {

    public static void main (String[] args) throws Throwable {

        DatabaseClient databaseClient = new DatabaseClient ();
        HttpClient httpClient1 = new HttpClient ("123.456.132.0");
        HttpClient httpClient2 = new HttpClient ("11.35.0.1");
        FileLogger fileLogger = new FileLogger ();
        UdpClient udpClient = new UdpClient ();

        String requestBody = "This is a request body";
        List<Class<?>> methodParameterTypes = Arrays.asList (new Class<?>[]{String.class});

        executeAll (groupExecutors (List.of (databaseClient, httpClient1, httpClient2, fileLogger, udpClient)
                , methodParameterTypes), requestBody);
    }


}
