package exercise.session8_proxies.external;

public interface HttpClient {

    void initialize();

    String sendRequest (String request);
}
