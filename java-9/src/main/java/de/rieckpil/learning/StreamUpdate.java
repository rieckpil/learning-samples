package de.rieckpil.learning;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamUpdate {

	public static void main(String[] args) {

		final IntStream intStream1 = IntStream.iterate(1, n -> n + 1);

		System.out.println(
				intStream1.takeWhile(n -> n < 10).mapToObj(Integer::toString).collect(Collectors.joining(", ")));

		final IntStream intStream2 = IntStream.rangeClosed(7, 14);

		System.out.println(
				intStream2.dropWhile(n -> n < 10).mapToObj(Integer::toString).collect(Collectors.joining(", ")));

		final IntStream intStream3 = IntStream.iterate(1, n -> n < 13, n -> n + 1);

		System.out.println(intStream3.mapToObj(Integer::toString).collect(Collectors.joining(", ")));
	}

}
