package de.rieckpil.microprofile.customer.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.opentracing.Traced;

@Path("/customer")
public class CustomerResource {

	@GET
	public String sayHello() {
		return "Hello World";
	}

	@GET
	@Traced
	@Path("/{id}")
	public String getSpecificHelloWorld(@PathParam("id") String id) {
		return "Hello World " + id;
	}
}
