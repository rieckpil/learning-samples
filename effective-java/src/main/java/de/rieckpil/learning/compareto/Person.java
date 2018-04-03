package de.rieckpil.learning.compareto;

public class Person implements Comparable<Person> {

	private int age;
	private String name;

	public Person() {

	}

	public Person(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}

	@Override
	public int compareTo(Person person) {

		if (this.age < person.getAge()) {
			return -1;
		} else if (this.age == person.getAge()) {
			return 0;
		} else {
			return 1;
		}
	}

}
