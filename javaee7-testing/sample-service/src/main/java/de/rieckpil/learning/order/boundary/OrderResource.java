package de.rieckpil.learning.order.boundary;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("orders")
@Stateless
public class OrderResource {

	@Inject
	OrderProcessor orderProcessor;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllOrders() {
		orderProcessor.order();

		JsonObject object = Json.createObjectBuilder().add("number", "42").build();

		return Response.ok(object).build();
	}

}
