package de.rieckpil.learning.control;

import de.rieckpil.learning.entity.CarCreated;

import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;

public class CarCreationListener {

    public void onCarCreated(@Observes CarCreated carCreated) {
        System.out.println("New car got created: " + carCreated.getIdentifier());
    }

    public void onCarCreatedAsync(@ObservesAsync CarCreated carCreated) {
        System.out.println("ASYNC: New car got created: " + carCreated.getIdentifier());
    }
}
