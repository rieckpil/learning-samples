/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks.items.boundary;

import com.airhacks.items.entity.Producer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author Philip
 */
@Path("producers")
public class ProducerResource {
    
    
    @PersistenceContext
    EntityManager em;
    
    @GET
    @Path("count")
    public Response getAmountOfProducer(){
        return Response.ok(em.createNamedQuery("COUNT_ALL_PRODUCER", Producer.class).getSingleResult()).build();
    }
}
