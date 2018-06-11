package de.rieckpil.learning.highperformancejpa;

import org.postgresql.ds.PGSimpleDataSource;

public class PostgresJdbcConfiguration {

    public void configure() {

        PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();
        pgSimpleDataSource.setPreparedStatementCacheQueries(255);
        pgSimpleDataSource.setPreparedStatementCacheSizeMiB(10);

    }
}
