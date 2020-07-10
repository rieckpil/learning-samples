package de.rieckpil.blog;

import javax.annotation.Resource;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

@Singleton
@DataSourceDefinition(
  name = "java:app/jdbc/psql",
  className = "org.postgresql.xa.PGXADataSource",
  user = "${DATABASE_USERNAME}",
  password = "${DATABASE_PASSWORD}",
  serverName = "${DATABASE_HOST:172.17.0.1}",
  portNumber = 5432,
  databaseName = "${DATABASE_DATABASENAME:postgres}")
public class DatabaseConfig {

  @Resource(lookup="java:app/jdbc/psql")
  DataSource ds;

  @Produces
  public DataSource getDatasource() {
    return ds;
  }
}
