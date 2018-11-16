package de.rieckpil.learning.codingchallenges.java;

import org.junit.Test;

public class BitwiseOperator {

	@Test
	public void test() {

		int a = 60;
		int b = 13;

		System.out.println(1 << 4);
		System.out.println(1 << 30);
		System.out.println("Amount of CPUs: " + Runtime.getRuntime().availableProcessors());
		System.out.println(Integer.MAX_VALUE - 8);
		System.out.println("XOR: " + (a ^ b));
		System.out.println("AND: " + (a & b));
		System.out.println("OR: " + (a | b));
		System.out.println(Long.valueOf("1").hashCode());
		System.out.println(Long.valueOf("1").hashCode());

	}
}
