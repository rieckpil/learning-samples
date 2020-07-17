package de.rieckpil.learning;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class LifecycleTest {

  public LifecycleTest() {
    System.out.println("Constructor called!");
  }

  @Test
  @Disabled
  public void test1() {
    System.out.println("test1");
    assertEquals(4, 2 + 2);
  }

  @Test
  public void test2() {
    System.out.println("test2");
    assertEquals(4, 2 + 2);
  }
}
