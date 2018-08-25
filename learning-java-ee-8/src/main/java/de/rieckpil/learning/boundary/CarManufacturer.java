package de.rieckpil.learning.boundary;

import de.rieckpil.learning.control.CarFactory;
import de.rieckpil.learning.control.CarRepository;
import de.rieckpil.learning.entity.Car;
import de.rieckpil.learning.entity.CarCreated;
import de.rieckpil.learning.entity.Specification;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CarManufacturer {

    @Inject
    CarFactory carFactory = new CarFactory();

    @Inject
    CarRepository carRepository;

    @Inject
    Event<CarCreated> carCreatedEvent;

    public Car manufactureCar(Specification specification) {
        Car car = carFactory.createCar(specification);

        // store car
        carRepository.store(car);
        carCreatedEvent.fire(new CarCreated(car.getIdentifier()));
        return car;

    }

    public List<Car> retrieveCars() {
        return null;
    }

    public Car retrieveCar(String id) {
        return null;
    }
}
