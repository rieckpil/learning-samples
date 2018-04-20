package com.airhacks.trainers.boundary;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.airhacks.trainers.control.TrainersCatalog;

@Path("trainers")
public class TrainersResource {

    @Inject
    TrainersCatalog trainersCatalog;

    @GET
    public String getTrainers() {
        return this.trainersCatalog.getAllTrainers();
    }

}