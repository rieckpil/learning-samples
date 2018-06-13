package com.airhacks.ping.boundary;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author airhacks.com
 */
@Path("ping")
public class PingResource {

    @GET
    public String ping() {
        return "Enjoy Java EE 8!";
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/persons")
    public Response getAllPersons() {

        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        JsonArray jsonArray = jsonArrayBuilder.add(10).add(11).add(12).build();

        return Response.ok(jsonArray).build();

    }

}
