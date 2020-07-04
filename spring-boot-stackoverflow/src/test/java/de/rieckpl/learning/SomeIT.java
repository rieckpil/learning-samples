package de.rieckpl.learning;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SomeIT {

  public static GenericContainer postgreSQLContainer = new PostgreSQLContainer()
    .withReuse(true);

  @BeforeAll
  public static void beforeAll() {
    postgreSQLContainer.start();
  }

  @TestConfiguration
  static class Config {

    @Bean
    WebClient webClient() {
      return WebClient.builder().baseUrl("http://duke.de").build();
    }
  }

  @Autowired
  private WebClient webClient;

  @Test
  public void testMe() {
    System.out.println(webClient);
  }
}
