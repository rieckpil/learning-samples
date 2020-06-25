package de.rieckpl.learning;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@EnabledIf(
  expression = "${smoke.tests.enabled}",
  reason = "Disabled due to property",
  loadContext = true
)
public class DisabledIT {

  @Test
  public void testShouldBeDisabled() {
    assertEquals(2, 4/3);
  }
}
