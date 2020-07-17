package de.rieckpil.learning;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MyTest {

  @ParameterizedTest
  @MethodSource("methodSource")
  void test(final String input, final Integer index) {
    System.out.println(input + " " + index);
  }

  static Stream<Arguments> methodSource() {
    List<String> params = List.of("a", "b", "c");

    return IntStream.range(0, params.size())
      .mapToObj(index -> Arguments.arguments(params.get(index), index));
  }

  @Test
  public void testReporter(TestReporter testReporter) {
    System.out.println("Test" + testReporter.toString());
    System.out.println(testReporter.getClass().toString());
    testReporter.publishEntry("className  : " + this.getClass().toString());
  }
}
