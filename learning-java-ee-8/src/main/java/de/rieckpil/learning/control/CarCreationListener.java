package de.rieckpil.learning.control;

import de.rieckpil.learning.entity.CarCreated;

import javax.enterprise.event.Observes;

public class CarCreationListener {

    public void onCarCreated(@Observes CarCreated carCreated) {
        System.out.println("New car got created: " + carCreated.getIdentifier());
    }
}
