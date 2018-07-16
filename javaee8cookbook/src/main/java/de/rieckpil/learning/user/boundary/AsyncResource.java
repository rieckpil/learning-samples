package de.rieckpil.learning.user.boundary;

import de.rieckpil.learning.user.control.AsyncResultClient;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;


@Stateless
@Path("async")
public class AsyncResource {

    @Inject
    private AsyncResultClient client;

    @GET
    public void asyncService(@Suspended AsyncResponse response) {
        try {
            client.getResult().thenApply(this::readResponse).thenAccept(response::resume);
        } catch (Exception e) {
            response.resume(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build());
        }
    }

    private String readResponse(Response response) {
        return response.readEntity(String.class);
    }
}
