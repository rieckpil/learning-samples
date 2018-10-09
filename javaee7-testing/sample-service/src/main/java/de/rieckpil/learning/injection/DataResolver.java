package de.rieckpil.learning.injection;

import javax.annotation.PostConstruct;

public class DataResolver {

	@PostConstruct
	public void init() {
		System.out.println("init data resolver");
	}

	public String getData() {
		return "Hello";
	}

}
