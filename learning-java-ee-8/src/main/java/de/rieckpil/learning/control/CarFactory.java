package de.rieckpil.learning.control;

import de.rieckpil.learning.entity.Car;
import de.rieckpil.learning.entity.Color;
import de.rieckpil.learning.entity.Specification;

import javax.inject.Inject;
import java.util.UUID;

// @Dependent is added at default
public class CarFactory {

    @Inject
    @Diesel
    Color defaultColor;

    public Car createCar(Specification specification) {
        Car car = new Car();
        car.setIdentifier(UUID.randomUUID().toString());
        car.setColor(specification.getColor() == null ? defaultColor : specification.getColor());
        car.setEngineType(specification.getEngineType());
        return car;
    }
}
