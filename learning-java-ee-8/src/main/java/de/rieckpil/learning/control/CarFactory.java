package de.rieckpil.learning.control;

import de.rieckpil.learning.entity.Car;
import de.rieckpil.learning.entity.Color;
import de.rieckpil.learning.entity.Specification;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.UUID;

// @Dependent is added at default
public class CarFactory {

    @Inject
    @Diesel
    Color defaultColor;

    @Inject
    @Config("identifier.prefix")
    String indentifierPrefix;

    @Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = CarCreationException.class)
    public Car createCar(Specification specification) {
        Car car = new Car();
        car.setIdentifier(indentifierPrefix + "-" + UUID.randomUUID().toString());
        car.setColor(specification.getColor() == null ? defaultColor : specification.getColor());
        car.setEngineType(specification.getEngineType());
        return car;
    }
}
