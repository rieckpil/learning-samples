package de.rieckpil.learning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

@SpringBootTest
@ActiveProfiles("test")
public class InformationIT {

  @Autowired
  private Environment environment;

  @Test
  public void testEnv() {
    System.out.println(Arrays.toString(environment.getActiveProfiles()));
    System.out.println(environment.getProperty("secret.value"));
    System.out.println(environment.getProperty("secret.value.two"));
    System.out.println(environment.getProperty("smoke.tests.enabled"));
  }
}
