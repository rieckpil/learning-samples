package de.rieckpil.learning.control;

import de.rieckpil.learning.entity.Car;
import de.rieckpil.learning.entity.Color;
import de.rieckpil.learning.entity.Specification;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

// @Dependent is added at default
public class CarFactory {

    @Resource
    ManagedScheduledExecutorService managedScheduledExecutorService;

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

    @PostConstruct
    public void activateTimer() {
        managedScheduledExecutorService.scheduleAtFixedRate(this::doSomething, 60, 10, TimeUnit.MINUTES);
    }

    public void doSomething() {
        System.out.println("DO BAR");
    }
}
