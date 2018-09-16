package de.rieckpil.learning.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = { "name", "id" })
@ToString(of = { "id", "age", "name" })
public class Ping {

	private String name;
	private int age;
	private double price;
	private String id;

	public Ping() {
		super();
	}

	public Ping(String name, int age, double price, String id) {
		super();
		this.name = name;
		this.age = age;
		this.price = price;
		this.id = id;
	}
}
