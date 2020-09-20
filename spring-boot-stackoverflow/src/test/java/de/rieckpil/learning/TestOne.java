package de.rieckpil.learning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource(properties=
  {"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration"})
public class TestOne {

  @TestConfiguration
  static class TestConfig {

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
      return new RestTemplateBuilder().interceptors(new UrlRewriter());
    }

  }

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  public void test() {
    assertNotNull(testRestTemplate);

    testRestTemplate.getForObject("/public", String.class);
  }
}
