package exercise.session2_construcors.restricted_constructors.web;

import com.sun.net.httpserver.HttpServer;
import exercise.session2_construcors.restricted_constructors.ServerConfiguration;

import java.io.IOException;
import java.io.OutputStream;

public class WebServer {

    public void startServer () throws IOException {

        HttpServer httpServer = HttpServer.create (ServerConfiguration.getInstance ().getServerAddress (), 0);

        httpServer.createContext ("/greeting").setHandler (exchange -> {
            String response = ServerConfiguration.getInstance ().getGreetingMessage ();
            exchange.sendResponseHeaders (200, response.length ());

            OutputStream responseBody = exchange.getResponseBody ();
            responseBody.write (response.getBytes ());
            responseBody.flush ();
            responseBody.close ();
        });

        System.out.println (String.format("Starting server on address %s:%d",
                ServerConfiguration.getInstance ().getServerAddress ().getHostName (),
                ServerConfiguration.getInstance ().getServerAddress ().getPort ()));

        httpServer.start ();
    }

}
