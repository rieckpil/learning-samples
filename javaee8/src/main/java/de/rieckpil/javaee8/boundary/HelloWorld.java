package de.rieckpil.javaee8.boundary;

import de.rieckpil.javaee8.control.NameResolver;
import org.eclipse.microprofile.metrics.annotation.Counted;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
@Stateless
public class HelloWorld {

    @Inject
    private NameResolver nameResolver;

    @GET
    @Counted(name = "getName")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sayHello() {

        JsonObject result = Json.createObjectBuilder().add("name", nameResolver.getName()).build();

        return Response.status(200).entity(result).build();
    }

    @GET
    @Path("/system")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProperties() {

        JsonObjectBuilder builder = Json.createObjectBuilder();

        System.getProperties()
                .entrySet()
                .stream()
                .forEach(objectObjectEntry -> builder.add((String) objectObjectEntry.getKey(), (String)
                        objectObjectEntry.getValue()));

        JsonObject resultObject = builder.build();

        return Response.ok(resultObject).build();
    }

    @GET
    @Path("/call-hello")
    @Produces(MediaType.APPLICATION_JSON)
    public Response callHello() {

        Client client = ClientBuilder.newClient();

        Response response = client.target("http://localhost:9080/resources/hello").request().get();

        JsonObject object = response.readEntity(JsonObject.class);

        return Response.ok(object.get("name")).build();
    }
}
