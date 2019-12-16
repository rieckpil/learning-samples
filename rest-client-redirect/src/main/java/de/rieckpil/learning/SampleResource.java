package de.rieckpil.learning;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.net.URI;
import java.net.URISyntaxException;

@Path("sample")
public class SampleResource {

    @Inject
    @ConfigProperty(name = "message")
    private String message;

    @GET
    public Response message() throws URISyntaxException {
        System.out.println("HIT");
        return Response.temporaryRedirect(new URI("http://localhost:9080/sample/data")).build();
    }

    @GET
    @Path("data")
    public Response data() {
        return Response.ok(message).build();
    }
}
