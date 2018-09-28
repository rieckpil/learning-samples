package de.rieckpil.learning.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/helloworld")
public class SampleController {
	
	@GetMapping
	public String sayHello() {
		return "Hi!";
	}

}
