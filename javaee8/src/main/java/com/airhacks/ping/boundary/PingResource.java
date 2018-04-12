package com.airhacks.ping.boundary;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.airhacks.ping.entity.Ping;

/**
 *
 * @author airhacks.com
 */
@Path("ping")
@Stateless
public class PingResource {
	
	@Inject
	PingManager pingManager;

	@GET
	public String ping() {
		return "Enjoy Java EE 8!";
	}

	@GET
	@Produces("application/json")
	@Path("/{name}")
	public Response returnJson(@PathParam("name") String name) {

		JsonObject result = Json.createObjectBuilder().add("name", name).build();

		return Response.status(200).entity(result).build();

	}
	
	@POST
	public Response createPing(JsonObject pingAsJson, @Context UriInfo info) {
		
		pingManager.savePing(new Ping(pingAsJson));
		
		return Response.status(201).entity(pingAsJson).build();
	}
}
