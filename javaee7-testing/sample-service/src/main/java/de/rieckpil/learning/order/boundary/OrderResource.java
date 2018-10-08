package de.rieckpil.learning.order.boundary;

import java.net.URI;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("orders")
@Singleton
public class OrderResource {

	@Inject
	OrderProcessor orderProcessor;

	ConcurrentHashMap<String, JsonObject> noSQLDb;

	@PostConstruct
	public void init() {
		this.noSQLDb = new ConcurrentHashMap<>();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllOrders() {
		orderProcessor.order();

		JsonObject object = Json.createObjectBuilder().add("number", "42").build();

		return Response.ok(object).build();
	}
	
	@GET
	@Path("{id}")
	public Response find(@PathParam("id") String id) {
		return Response.ok(noSQLDb.get(id)).build();
	}

	@POST
	public Response save(JsonObject payload, @Context UriInfo urInfo) {

		String key = UUID.randomUUID().toString();
		
		this.noSQLDb.put(key, payload);
		
		URI location = urInfo.getAbsolutePathBuilder().path("/" + key).build();

		return Response.created(location).build();

	}

}
