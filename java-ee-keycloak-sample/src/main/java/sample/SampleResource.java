package sample;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.annotation.Metered;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("sample")
@Stateless
public class SampleResource {

    @Inject
    @ConfigProperty(name = "message")
    private String message;

    @Inject
    private SampleManager sampleManager;

    @GET
    @Metered(name = "sample", displayName = "sample")
    public Response message() {
        System.out.println("--- test");
        return Response.ok(sampleManager.getName() + ":" + message).build();
    }

}
