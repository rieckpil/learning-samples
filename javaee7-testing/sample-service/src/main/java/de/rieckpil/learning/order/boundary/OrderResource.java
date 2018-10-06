package de.rieckpil.learning.order.boundary;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("orders")
@Stateless
public class OrderResource {

	@GET
	public Response getAllOrders() {
		return Response.ok().build();
	}

}
