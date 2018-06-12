package de.rieckpil.learning.highperformancejpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;

@Component
public class JdbcStatementFiller implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {


        PreparedStatement preparedStatement = jdbcTemplate.getDataSource().getConnection().prepareStatement("INSERT INTO person (name) VALUES (?)");
        preparedStatement.setString(1, "Paul");
        
        boolean isFirstResultAResultSet = preparedStatement.execute();

        System.out.println("isFirstResultAResultSet = " + isFirstResultAResultSet);

    }
}
