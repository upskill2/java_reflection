package exercise.session2_construcors.restricted_constructors;

import java.net.InetSocketAddress;

public class ServerConfiguration {

    private static ServerConfiguration serverConfigurationInstance;
    private final InetSocketAddress serverAddress;
    private final String greetingMessage;

    private ServerConfiguration (int port, String greetingMessage) {
        this.serverAddress = new InetSocketAddress ("localhost", port);
        this.greetingMessage = greetingMessage;

        if (serverConfigurationInstance == null) {
            serverConfigurationInstance = this;
        }
    }

    public static ServerConfiguration getInstance () {
        return serverConfigurationInstance;
    }

    public InetSocketAddress getServerAddress () {
        return serverAddress;
    }

    public String getGreetingMessage () {
        return greetingMessage;
    }
}
