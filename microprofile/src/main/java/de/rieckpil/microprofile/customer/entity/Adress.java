package de.rieckpil.microprofile.customer.entity;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;

public class Adress {
	
	private int postalCode;
	private String street;
	private String city;
	
	public Adress(int postalCode, String street, String city) {
		super();
		this.postalCode = postalCode;
		this.street = street;
		this.city = city;
	}

	public int getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public JsonValue toJSON() {
		JsonObject result = Json.createObjectBuilder()
				.add("street", this.street)
				.add("postalCode",this.postalCode)
				.add("city",this.city)
				.build();
		return result;
	}
	
}
