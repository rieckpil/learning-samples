package de.rieckpil.learning.entity;

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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	

}
