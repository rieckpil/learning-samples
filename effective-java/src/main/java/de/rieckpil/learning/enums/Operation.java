package de.rieckpil.learning.enums;

public enum Operation {

	PLUS("+") {
		public double apply(double x, double y) {
			return x + y;
		}
	},
	MINUS("-") {
		public double apply(double x, double y) {
			return x - y;
		}
	},
	TIMES("*") {
		public double apply(double x, double y) {
			return x * y;
		}
	},
	DIVIDE("/") {
		public double apply(double x, double y) {
			return x / y;
		}
	};

	private final String symbol;

	private Operation(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return this.symbol;
	}

	public abstract double apply(double x, double y);
}
