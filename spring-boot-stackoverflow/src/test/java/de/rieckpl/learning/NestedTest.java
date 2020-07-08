package de.rieckpl.learning;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NestedTest {

  @Nested
  @DisplayName("Test for calculator")
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
