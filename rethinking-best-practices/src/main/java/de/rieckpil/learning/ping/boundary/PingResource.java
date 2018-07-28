package de.rieckpil.learning.ping.boundary;

import de.rieckpil.learning.ping.control.PingService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("ping")
@Stateless
public class PingResource {

    @Inject
    private PingService pingService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response ping() {

        System.out.println("incoming call");

        return Response.ok(pingService.getPing()).build();
    }
}

