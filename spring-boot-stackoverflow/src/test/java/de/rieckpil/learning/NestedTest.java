package de.rieckpil.learning;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NestedTest {

  @Nested
  @DisplayName("Test for calculator")
  @SpringBootTest
  @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
  class CalculatorTest {

    @Test
    public void testOne() {
      assertEquals(2, 1 + 1);
    }

    @Test
    public void testTwo() {
      assertEquals(2, 3 - 1);
    }
  }


  @Nested
  @DisplayName("Test for division")
  @SpringBootTest
  @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
  class DivisionTest {

    @Test
    public void testOne() {
      assertEquals(2, 4 / 2);
    }

    @Test
    public void testTwo() {
      assertEquals(2, 8 / 4);
    }
  }
}
