package de.rieckpil.learning;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(PropertyExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBootStackoverflowApplication.class)
public class TestOne {

  @BeforeAll
  public static void beforeAll() {
    System.out.println("Before All");
  }

  static {
    System.out.println("Static code access");
  }

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  public void test() {
    assertNotNull(testRestTemplate);
  }
}
