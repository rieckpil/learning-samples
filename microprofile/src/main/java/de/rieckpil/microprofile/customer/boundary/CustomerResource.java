package de.rieckpil.microprofile.customer.boundary;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.opentracing.Traced;

import de.rieckpil.microprofile.customer.entity.Customer;

@Path("/customers")
@RequestScoped
@Counted
public class CustomerResource {

	@Inject
	private CustomerStore customerStore;

	@GET
	@Produces("application/json")
	public Response getAllCustomers() {

		JsonArrayBuilder retVal = Json.createArrayBuilder();
		customerStore.getAllCustomers().stream().map(Customer::toJSON).forEach(retVal::add);
		return Response.ok(retVal.build()).build();
	}

	@GET
	@Traced
	@Path("/{id}")
	public String getSpecificHelloWorld(@PathParam("id") String id) {
		return "Hello World " + id;
	}
}
