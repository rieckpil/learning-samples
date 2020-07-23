package de.rieckpil.learning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

// if you are using JUnit 4 this is also needed
// @RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebTestClientAccessTest {

  @Autowired
  private WebTestClient webTestClient;

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  public void notNull() {
    assertNotNull(webTestClient);
    assertNotNull(testRestTemplate);
  }
}
