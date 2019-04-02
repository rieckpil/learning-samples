package de.rieckpil.learning;

import java.util.Optional;

public class OptionalExample {

	public static void main(String[] args) {

		final Optional<String> user = Optional.of("Mike");
		final Optional<String> emptyUser = Optional.empty();

		user.ifPresentOrElse(System.out::println, () -> System.out.println("Nothing found"));
		emptyUser.ifPresentOrElse(System.out::println, () -> System.out.println("Nothing found"));
	}
}
