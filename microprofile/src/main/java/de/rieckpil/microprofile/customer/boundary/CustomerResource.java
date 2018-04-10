package de.rieckpil.microprofile.customer.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/customer")
public class CustomerResource {
	
	@GET
	public String sayHello() {
		return "Hello World";
	}
}
