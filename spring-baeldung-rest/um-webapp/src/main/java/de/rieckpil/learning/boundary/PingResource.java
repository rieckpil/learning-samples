package de.rieckpil.learning.boundary;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/pings")
public class PingResource {
	
	@GetMapping
	public String sayHello() {
		return "Hello World";
	}

}
