/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks.shop.boundary;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonPatch;
import javax.json.JsonPatchBuilder;
import javax.json.JsonPointer;
import javax.json.JsonValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author Philip
 */
@Path("shops")
public class ShopResource {

    @Inject
    ItemsStore itemsStore;

    @GET
    public Response getLatestItemsForShop() {
        JsonArray items = itemsStore.getLatestItems();

        JsonPointer pointer = Json.createPointer("/0/producer/name");
        JsonValue value = pointer.getValue(items);

        return Response.ok(value).build();
    }

    @GET
    @Path("modify")
    public Response modifyLatestItemsorShop() {
        JsonArray items = itemsStore.getLatestItems();

        JsonPatchBuilder builder = Json.createPatchBuilder();
        JsonPatch patch = builder.replace("/0/producer/name", "Sport Hoffmann").remove("/1/producer").build();
        JsonArray modifiedItems = patch.apply(items);
        
        return Response.ok(modifiedItems).build();
    }

}
