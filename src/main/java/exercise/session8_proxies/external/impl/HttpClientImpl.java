package exercise.session8_proxies.external.impl;

import exercise.session8_proxies.external.HttpClient;

public class HttpClientImpl implements HttpClient {
    @Override
    public void initialize () {
        System.out.println ("Initializing Http client");

        try {
            Thread.sleep (500);
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
    }

    @Override
    public String sendRequest (final String request) {
        System.out.println (String.format ("Sending request %s", request));

        try {
            Thread.sleep (1000);
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }

        System.out.println ("Received response");
        return "someResponse data";
    }
}
