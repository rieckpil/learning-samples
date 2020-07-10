package de.rieckpil.blog;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.SQLException;

@Singleton
@Startup
public class DatabaseCheck {

  @Inject
  private DataSource dataSource;

  @PostConstruct
  public void check() throws SQLException {
    System.out.println(dataSource.getConnection().getMetaData().getDatabaseMajorVersion());
  }
}
