package de.rieckpil.learning.enums;

public class EnumOperationRunner {

	public static void main(String[] args) {

		double x = 5.0;
		double y = 1.5;

		for (Operation op : Operation.values()) {
			System.out.printf("%f %s %f = %f %n", x, op, y, op.apply(x, y));
		}

	}

}
