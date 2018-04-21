package com.airhacks.ping.entity;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
//@NamedQuery(name = Ping.ALL, query = "SELECT f FROM Flight f")
public class Ping {

	private final static String PREFIX = "com.airhacks.ping.entity.Ping.";
	public final static String ALL = PREFIX + "findAll";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private int age;

	public Ping() {
	}

	public Ping(JsonObject pingAsJson) {
		this.name = pingAsJson.getString("name", "undefined");
		this.age = Integer.parseInt(pingAsJson.getString("age"));
	}

	public Ping(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public JsonObject toJSON() {
		return Json.createObjectBuilder().add("name", this.name).add("age", this.age).build();
	}

}
