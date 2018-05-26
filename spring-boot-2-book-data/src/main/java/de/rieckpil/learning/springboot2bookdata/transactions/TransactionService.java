package de.rieckpil.learning.springboot2bookdata.transactions;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Profile("h2")
public class TransactionService {

    private final JdbcTemplate jdbcTemplate;

    public TransactionService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void tryInsert() {

        this.jdbcTemplate.update("INSERT INTO person (name) values (?)", "Mike");

        throw new RuntimeException("Ooops... an error happened!");
    }

    @Transactional
    public void doInsert() {

        this.jdbcTemplate.update("INSERT INTO person (name) values (?)", "Mike");

    }
}
