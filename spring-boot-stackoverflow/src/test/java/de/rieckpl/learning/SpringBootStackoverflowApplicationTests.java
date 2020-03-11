package de.rieckpl.learning;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(initializers = MongoDbDataInitializer.class)
class SpringBootStackoverflowApplicationTests {

  @Test
  void contextLoads() {
  }

}
