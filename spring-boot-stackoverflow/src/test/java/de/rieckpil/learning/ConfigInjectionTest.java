package de.rieckpil.learning;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = "foo.bar=dukes")
public class ConfigInjectionTest {

  @Autowired
  private Environment environment;

  @Test
  public void testMe() {
    String property = environment.getProperty("foo.bar");
    System.out.println(property);
    Assertions.assertNotNull(property);
  }
}
