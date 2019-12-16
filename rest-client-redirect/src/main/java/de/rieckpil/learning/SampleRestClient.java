package de.rieckpil.learning;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("resources")
@RegisterRestClient(baseUri = "http://localhost:9081")
public interface SampleRestClient {

    @GET
    @Path("sample")
    Response fetchData();
}
