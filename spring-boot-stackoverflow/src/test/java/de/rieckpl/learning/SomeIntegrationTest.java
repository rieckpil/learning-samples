package de.rieckpl.learning;

import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.engine.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.testcontainers.containers.GenericContainer;

import java.time.Duration;

@SpringBootTest
public class SomeIntegrationTest {

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
