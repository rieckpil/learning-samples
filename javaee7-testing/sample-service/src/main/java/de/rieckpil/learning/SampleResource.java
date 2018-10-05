package de.rieckpil.learning;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("sample")
public class SampleResource {

	@Inject
	@ConfigProperty(name = "message")
	private String message;

	@GET
	public Response samplmeMessage() {
		return Response.ok(message).build();
	}

	@GET
	@Path("/hello")
	public Response newEndpoint() {
		return Response.ok("New Endpoint!111!").build();
	}

}
