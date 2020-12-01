package de.rieckpil.learning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockRestServiceServer
@ContextConfiguration(initializers = MongoDbDataInitializer.class)
class ApplicationIT {

  @Autowired
  private WebTestClient webTestClient;

  @Autowired
  private Environment environment;

  @Test
  void testOne() {
    assertNotNull(environment);
  }

  @Test
  void testTwo() {
    assertNotNull(environment);
  }

  @Test
  void testThree() {
    assertNotNull(environment);
  }
}
