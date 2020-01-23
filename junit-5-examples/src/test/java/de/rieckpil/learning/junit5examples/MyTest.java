package de.rieckpil.learning.junit5examples;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MyExtension.class)
public class MyTest {

  String name;

  @BeforeEach
  public void setup(String s) {

  }

  @Test
  public void foo(String s) {
    System.out.println(name);
    System.out.println(s);
    assertNotNull(s);
  }

  @Test
  public void bar(String s) {
    System.out.println(s);
    assertNotNull(s);
  }

}
