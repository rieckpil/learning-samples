package de.rieckpil.learning.user.boundary;

import de.rieckpil.learning.user.control.AsyncResultClient;
import de.rieckpil.learning.user.control.UserService;
import de.rieckpil.learning.user.entity.User;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedThreadFactory;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;


@Stateless
@Path("async")
public class AsyncResource {

    @Inject
    private AsyncResultClient client;

    @Inject
    private UserService userService;

    @Resource
    private ManagedThreadFactory managedThreadFactory;

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

    @GET
    @Path("thread")
    public void threadAsync(@Suspended AsyncResponse response) {

        Thread thread = managedThreadFactory.newThread(() -> {
            response.resume(Response.ok("managed").build());
        });

        thread.setName("Managed Async Task");
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }

    @GET
    @Path("wscall")
    public void websocketCall(@Suspended AsyncResponse response){

        System.out.println("trying to call websocket");

        WebsocketClient client = new WebsocketClient(response);
        client.connect();
        client.send("Message from client " + new Date().getTime());
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("future")
    public void futureCall(@Suspended AsyncResponse response){
        Future<User> result = userService.getFutureUser();
        try {
            response.resume(Response.ok(result.get()).build());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
