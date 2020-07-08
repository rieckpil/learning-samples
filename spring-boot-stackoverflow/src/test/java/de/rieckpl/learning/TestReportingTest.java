package de.rieckpl.learning;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestReportingTest {

  @Test
  public void testOutput() {
    System.out.println("Key One: Value");
    System.out.println("Key Two: 1,2,3,4,5");
    assertEquals(4, 2 + 2);
  }
}
