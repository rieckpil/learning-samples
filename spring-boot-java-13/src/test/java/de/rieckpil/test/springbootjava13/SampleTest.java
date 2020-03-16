package de.rieckpil.test.springbootjava13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SampleTest {

  @Test
  public void testJdk13PreviewFeature() {

    String json = """
      {"name": "duke"}
    """;

    assertNotNull(json);
  }
}
