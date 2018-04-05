package de.rieckpil.learning.interfaces;

public interface DefaultMethodInterface {

	public void sayHello(String name);

	public default void bark(String name) {
		System.out.println("Woof - " + name);
	}

	public default void miau(String name) {
		System.out.println("Miau - " + name);
	}

}