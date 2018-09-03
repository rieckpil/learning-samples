package de.rieckpil.learning.control;

import de.rieckpil.learning.entity.Car;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import java.util.concurrent.locks.LockSupport;

@Stateless
public class CarProcessor {

    @Asynchronous
    public void processNewCar(Car car) {
        LockSupport.parkNanos(2_000_000_000L);
    }
}
