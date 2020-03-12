package de.rieckpl.learning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(initializers = MongoDbDataInitializer.class)
class SpringBootStackoverflowApplicationTests {

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
