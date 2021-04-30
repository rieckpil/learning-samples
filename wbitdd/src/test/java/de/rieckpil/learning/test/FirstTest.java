package de.rieckpil.learning.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FirstTest {

  @Test
  void failingTest() {
    assertEquals(0, 4 % 3);
  }

  @Test
  void successfulTest() {
    assertEquals(1, 4 % 3);
  }

}
