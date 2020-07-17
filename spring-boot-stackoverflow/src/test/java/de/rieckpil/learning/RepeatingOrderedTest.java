package de.rieckpil.learning;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RepeatingOrderedTest {

  @Test
  @Order(1)
  @RepeatedTest(2)
  public void testOne() {
    System.out.println("Test1");
    assertEquals(4, 2 + 2);
  }

  @Test
  @Order(2)
  @RepeatedTest(2)
  public void testTwo() {
    System.out.println("Test Two");
    assertEquals(4, 2 + 2);
  }
}
