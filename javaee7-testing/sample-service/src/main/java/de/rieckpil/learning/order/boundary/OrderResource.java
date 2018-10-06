package de.rieckpil.learning.order.boundary;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("orders")
@Stateless
public class OrderResource {

	@Inject
	OrderProcessor orderProcessor;

	@GET
	public Response getAllOrders() {
		orderProcessor.order();
		return Response.ok().build();
	}

}
