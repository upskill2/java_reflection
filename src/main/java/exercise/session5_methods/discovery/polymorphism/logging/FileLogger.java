package exercise.session5_methods.discovery.polymorphism.logging;

import java.io.IOException;

public class FileLogger {
    public void sendRequest (String data) throws IOException {
        throw new IOException ("File system is not available");
        //   System.out.println (String.format ("Data : %s was logged to the file system", data));
    }
}
