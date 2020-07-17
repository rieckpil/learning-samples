package de.rieckpil.learning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.reactive.function.client.WebClient;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

// @ExtendWith(SpringExtension.class)
@SpringJUnitConfig(value = {ExternalTestConfig.class, SpringExtTest.TestConfig.class})
public class SpringExtTest {

  @Autowired
  private Environment environment;

  @Autowired
  private WebClient webClient;

  @Autowired
  private DataSource dataSource;

  @TestConfiguration
  static class TestConfig {
    @Bean
    public WebClient webClient() {
      return WebClient.builder().baseUrl("http://rieckpil.de").build();
    }
  }

  @Test
  public void testMe() {
    assertNotNull(environment);
    assertNotNull(dataSource);
    assertNotNull(webClient);
  }
}
