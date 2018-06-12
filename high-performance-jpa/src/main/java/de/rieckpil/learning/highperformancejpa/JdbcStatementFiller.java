package de.rieckpil.learning.highperformancejpa;

import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcStatementFiller implements CommandLineRunner {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public void run(String... args) throws Exception {

    boolean isFirstResultAResultSet = jdbcTemplate.getDataSource().getConnection().createStatement().execute("INSERT INTO person (name) VALUES ('Hans')");
    System.out.println("isFirstResultAResultSet = " + isFirstResultAResultSet);

    PreparedStatement preparedStatement = jdbcTemplate.getDataSource().getConnection().prepareStatement("INSERT INTO person (name) VALUES (?)");
    preparedStatement.setString(1, "Paul");

    isFirstResultAResultSet = preparedStatement.execute();

    System.out.println("isFirstResultAResultSet = " + isFirstResultAResultSet);

  }
}
