package com.airhacks.items.boundary;

import com.airhacks.items.entity.Item;
import com.airhacks.items.entity.Producer;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("items")
public class ItemsResource {
    
    @PersistenceContext
    EntityManager em;
    
    @GET
    public Response getItems() {

        if(ThreadLocalRandom.current().nextInt(0, 10) != 1){
            return Response.ok("duke, fluke").build();
        }else{
            return Response.status(500).build();
        }

    }
    
    @GET
    @Path("newest")
    public Response getNewestItems() {
        
        List<Item> itemsFromDb = this.em.createNamedQuery("SELECT_ALL", Item.class).getResultList();
    
        return Response.ok(itemsFromDb).build();
        
    }

}