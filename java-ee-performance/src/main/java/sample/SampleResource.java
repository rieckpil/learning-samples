package sample;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("sample")
@Stateless
public class SampleResource {

    @Inject
    Event<String> event;

    @GET
    public Response message() {
        event.fire("Hello");
        System.out.println("Finish");
        return Response.ok("Hello World").build();
    }

}
