package exercise.session5_methods.discovery.polymorphism.http;

public class HttpClient {
    private String serverAddress;

    public HttpClient (String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public boolean sendRequest (String data) {
        System.out.println (String.format ("Request with body: %s was successfully sent to the server with address %s",
                data, serverAddress));
        return true;
    }

}
