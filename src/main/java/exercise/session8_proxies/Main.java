package exercise.session8_proxies;

import exercise.session8_proxies.external.DatabaseReader;
import exercise.session8_proxies.external.HttpClient;
import exercise.session8_proxies.external.impl.DatabaseReaderImpl;
import exercise.session8_proxies.external.impl.HttpClientImpl;

import java.io.IOException;

import static exercise.session8_proxies.utils.Utils.*;

public class Main {

    public static void main (String[] args) throws InterruptedException, IOException {

/*        HttpClient httpClient = createProxy (new HttpClientImpl ()) ;
        useHttpClient (httpClient);*/

/*        System.out.println ("-------------------");
        DatabaseReader databaseReader =  (new DatabaseReaderImpl ()) ;
        useDatabaseReader (databaseReader);*/

        System.out.println ("-------------------");
        DatabaseReader databaseReader1 = createProxy (new DatabaseReaderImpl ()) ;
        useDatabaseReader (databaseReader1);

    }
}
