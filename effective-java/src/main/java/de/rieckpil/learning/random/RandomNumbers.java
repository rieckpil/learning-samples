package de.rieckpil.learning.random;

import java.util.concurrent.ThreadLocalRandom;

public class RandomNumbers {

	public static void main(String[] args) {
		ThreadLocalRandom.current().ints(1_000, 0, 10_000).forEach(i -> {
			System.out.println(i);
		});

		System.out.println("### next int: " + ThreadLocalRandom.current().nextInt(1_000));
	}
}
