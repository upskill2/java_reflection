package exercise.session2_construcors.restricted_constructors;

import exercise.session2_construcors.restricted_constructors.web.WebServer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static exercise.session2_construcors.restricted_constructors.Utils.initConfiguration;

public class Main {

    public static void main (String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {

        initConfiguration ();

        WebServer webServer = new WebServer ();
        webServer.startServer ();
    }


}
