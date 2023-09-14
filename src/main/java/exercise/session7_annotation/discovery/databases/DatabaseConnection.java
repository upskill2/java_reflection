package exercise.session7_annotation.discovery.databases;

import exercise.session7_annotation.discovery.annotations.InitializerClass;
import exercise.session7_annotation.discovery.annotations.InitializerMethod;

@InitializerClass
public class DatabaseConnection {

    @InitializerMethod
    public void connectRoDatabase1 () {
        System.out.println ("Connecting to database 1");
    }

    @InitializerMethod
    public void connectRoDatabase2 () {
        System.out.println ("Connecting to database 2");
    }

}
