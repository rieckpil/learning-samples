package com.airhacks.items.boundary;

import java.util.concurrent.ThreadLocalRandom;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("items")
public class ItemsResource {

    @GET
    public Response getItems() {

        if(ThreadLocalRandom.current().nextInt(0, 10) != 1){
            return Response.ok("duke, fluke").build();
        }else{
            return Response.status(500).build();
        }

    }

}