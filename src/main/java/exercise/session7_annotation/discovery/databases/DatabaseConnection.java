package exercise.session7_annotation.discovery.databases;

import exercise.session7_annotation.discovery.annotations.InitializerClass;
import exercise.session7_annotation.discovery.annotations.InitializerMethod;
import exercise.session7_annotation.discovery.annotations.RetryOperation;

import java.io.IOException;

@InitializerClass
public class DatabaseConnection {
    private int failCounter = 5;

    @InitializerMethod
    @RetryOperation (retryExceptions = {IOException.class},
            durationBetweenRetriesMs = 110,
            failureMessage = "Overridden default message",
            numberOfRetries = 5)
    public void connectRoDatabase1 () throws IOException {
        System.out.println ("Connecting to database 1");

        if (failCounter > 0) {
            failCounter--;
            throw new IOException ("Connection failed");
        }
        System.out.println ("Connected to database 1");
    }

    @InitializerMethod
    public void connectRoDatabase2 () {
        System.out.println ("Connecting to database 2");
    }

}
