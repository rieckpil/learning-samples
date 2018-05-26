package de.rieckpil.learning.springboot2bookdata.h2.jdbc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Profile("h2")
public class SimpleJdbcCall implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public SimpleJdbcCall(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void run(String... args) throws Exception {
        doInsert();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void doInsert() {

        this.jdbcTemplate.update("INSERT INTO person (name) values (?)", "Tom");
        this.jdbcTemplate.update("INSERT INTO person (name) values (?)", "Paul");
        this.jdbcTemplate.update("INSERT INTO person (name) values (?)", "John");

        // throw new RuntimeException("Ooops... an error happened!");
    }
}
