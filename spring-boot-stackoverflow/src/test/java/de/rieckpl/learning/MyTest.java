package de.rieckpl.learning;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MyTest {

  @ParameterizedTest
  @MethodSource("methodSource")
  void test(final String input, final Integer index) {
    System.out.println(input + " " + index);
  }

  static List<Arguments> methodSource() {
    List<String> params = List.of("a", "b", "c");

    return IntStream.range(0, params.size())
      .mapToObj(index -> Arguments.arguments(params.get(index), index))
      .collect(Collectors.toList());
  }
}
