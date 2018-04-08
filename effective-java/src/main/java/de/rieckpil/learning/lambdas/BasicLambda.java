package de.rieckpil.learning.lambdas;

import java.util.Arrays;
import java.util.List;

public class BasicLambda {

	/**
	 * Functional interfaces in java.util.Function
	 * 
	 * UnaryOperator<T> -> result and argument types are the same -> T aply (T t) -> String::toLowerCase
	 * BinaryOperator<T> -> result and argument types are the same -> T apply(T t1, T t2) -> BigInteger:add
	 * Predicate<T> -> takes an argument and returns a boolean -> boolean test(T t) -> Collection::isEmpty
	 * Function<T,R> -> argument and return type differ -> R apply (T t) -> Arrays::asList
 	 * Supplier<T> -> no arguments and returns a value -> T get() Instant::now
	 * Consumer<T> -> argument and no return value -> void accept(T t) -> Sytemt.out::println
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		List<String> words = Arrays.asList("Hallo", "Welt", "123", "Herzogenaurach", "Willkommen");
		
		words.sort((s1, s2) -> {
			return Integer.compare(s1.length(),s2.length());
		});
		
		System.out.println(words);
		
	}
}
