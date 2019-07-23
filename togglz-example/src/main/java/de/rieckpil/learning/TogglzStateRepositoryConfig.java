package de.rieckpil.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.jdbc.JDBCStateRepository;

import javax.sql.DataSource;

@Configuration
public class TogglzStateRepositoryConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public StateRepository stateRepository() {
        return new JDBCStateRepository(dataSource);
    }
}
