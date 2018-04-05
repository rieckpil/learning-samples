package de.rieckpil.learning.longnumbers;

public class LongNumbers {

	private static int debt = 1_000_003;
	private static double interestRate = 1.304_121_134;

	public static void main(String[] args) {
		System.out.println(debt * interestRate);
	}
}
