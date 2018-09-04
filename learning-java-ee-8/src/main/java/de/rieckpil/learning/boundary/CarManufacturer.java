package de.rieckpil.learning.boundary;

import de.rieckpil.learning.control.*;
import de.rieckpil.learning.entity.Car;
import de.rieckpil.learning.entity.CarCreated;
import de.rieckpil.learning.entity.Specification;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CarManufacturer {

    @Inject
    CarFactory carFactory = new CarFactory();

    @Inject
    CarRepository carRepository;

    @Inject
    Event<CarCreated> carCreatedEvent;

    @Inject
    FatalLogger fatalLogger;

    @Inject
    CarProcessor carProcessor;

    @Resource
    ManagedExecutorService managedExecutorService;

    @PersistenceContext
    EntityManager entityManager;

    @Tracked
    public Car manufactureCar(Specification specification) {
        try {
            Car car = carFactory.createCar(specification);
            entityManager.persist(car);
            // store car
            carRepository.store(car);
            carCreatedEvent.fire(new CarCreated(car.getIdentifier()));
            carCreatedEvent.fireAsync(new CarCreated(car.getIdentifier()));
            managedExecutorService.execute(() -> carProcessor.processNewCar(car));
            // carProcessor.processNewCar(car);
            carProcessor.processNewCarAsync(car);
            System.out.println("CAR GOT MANUFACTURED");
            return car;
        } catch (Exception e) {
            fatalLogger.fatal(e);
            return null;
        }
    }

    public List<Car> retrieveCars() {
        return entityManager.createNamedQuery(Car.FIND_ALL, Car.class).getResultList();
    }

    public Car retrieveCar(String id) {
        return null;
    }
}
