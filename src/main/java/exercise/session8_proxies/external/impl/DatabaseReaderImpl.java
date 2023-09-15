package exercise.session8_proxies.external.impl;

import exercise.session8_proxies.external.DatabaseReader;

import java.io.IOException;

public class DatabaseReaderImpl implements DatabaseReader {
    @Override
    public int countRowsInTable (final String tableName) throws InterruptedException, IOException {
        System.out.println (String.format ("DatabaseReaderImpl - counting rows in table %s", tableName));

        throw new IOException ("Database connection error");

/*        Thread.sleep (500);
        return 50;*/
    }

    @Override
    public String[] readRow (final String sqlQuery) throws InterruptedException {
        System.out.println (String.format ("DatabaseReaderImpl - Executing SQL query :%s", sqlQuery));

        Thread.sleep (600);

        return new String[]{"column1", "column2", "column3"};
    }
}
