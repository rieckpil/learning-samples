package de.rieckpil.learning.interfaces;

public class DefaultMethodInterfaceImpl implements DefaultMethodInterface {

	public static void main(String[] args) {
		
		DefaultMethodInterfaceImpl test = new DefaultMethodInterfaceImpl();

		String name = "Phil";
		test.sayHello(name);
		test.bark(name);
		test.miau(name);
		
	}

	@Override
	public void sayHello(String name) {
		System.out.println("Hi, my name is " + name);
	}

}
