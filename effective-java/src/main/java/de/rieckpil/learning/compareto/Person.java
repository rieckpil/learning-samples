package de.rieckpil.learning.compareto;

public class Person implements Comparable<Person> {

	private int age;
	private String name;

	public Person(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	@Override
	public int compareTo(Person person) {
		return Integer.compare(this.age, person.getAge());
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}

}
