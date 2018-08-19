package de.rieckpil.learning.products.boundary;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("products")
public class ProductResource {

    @GET
    public Response getAllProducts() {
        JsonObject json = Json.createObjectBuilder().add("name", "coffee").add("price", 42.0).build();
        return Response.ok(json).build();
    }
}
