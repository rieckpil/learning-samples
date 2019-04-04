package de.rieckpil.learning;

import java.util.Objects;

public class ObjectsUpdate {

	public static void main(String[] args) {

		doFoo(null);
		doFoo("Hello World");

		System.out.println(ObjectsUpdate.class.getPackageName());

	}

	public static void doFoo(String msg) {

		String message = Objects.requireNonNullElse(msg, "Default");

		System.out.println(message);
	}
}
