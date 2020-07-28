package de.rieckpil.learning.context;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class ContextOneIT {

  @Test
  public void testMe() {
    System.out.println("Works");
  }
}
