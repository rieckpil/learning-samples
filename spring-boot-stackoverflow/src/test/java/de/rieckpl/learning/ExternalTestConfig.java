package de.rieckpl.learning;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@TestConfiguration
public class ExternalTestConfig {

  @Bean
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }

}
