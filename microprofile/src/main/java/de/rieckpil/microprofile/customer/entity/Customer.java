package de.rieckpil.microprofile.customer.entity;

import java.time.LocalDateTime;

import javax.json.Json;
import javax.json.JsonObject;

public class Customer {

	private String firstName;
	private String lastName;
	private LocalDateTime dob;
	private Adress adress;
	
	public Customer(String firstName, String lastName, LocalDateTime dob, Adress adress) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.adress = adress;
	}
	
	public JsonObject toJSON() {
		JsonObject result = Json.createObjectBuilder()
				.add("firstName", this.firstName)
				.add("lastName", this.lastName)
				.add("age",LocalDateTime.now().getYear() - this.dob.getYear())
				.add("adress", this.adress.toJSON()).build();
		return result;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDateTime getDob() {
		return dob;
	}
	public void setDob(LocalDateTime dob) {
		this.dob = dob;
	}
	public Adress getAdress() {
		return adress;
	}
	public void setAdress(Adress adress) {
		this.adress = adress;
	}
	
}
