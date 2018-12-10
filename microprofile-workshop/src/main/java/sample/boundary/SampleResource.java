package sample.boundary;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.annotation.Metered;
import sample.entity.Ping;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("sample")
public class SampleResource {

    @Inject
    SampleManager sampleManager;

    @Inject
    MetricRegistry metricRegistry;

    @Inject
    @ConfigProperty(name = "message", defaultValue = "Default Value")
    private String message;

    @GET
    @Metered
    public Response message() {
        return Response.ok(this.sampleManager.getNext() + " " + this.sampleManager.getFoo()).build();
    }

    @GET
    @Path("/ping")
    public Ping pingMessage() {
        return new Ping("Hello World!");
    }

}
