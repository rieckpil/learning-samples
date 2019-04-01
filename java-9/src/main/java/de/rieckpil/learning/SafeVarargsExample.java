package de.rieckpil.learning;

public class SafeVarargsExample {

	public static void main(String[] args) {
		printAll("Hello World", "42", 42, 99.99);
	}
	
	@SafeVarargs
	private static <T> void printAll(T... elements) {
		for (T elem : elements) {
			System.out.println(elem + " of type " + elem.getClass());
		}
	}

}
