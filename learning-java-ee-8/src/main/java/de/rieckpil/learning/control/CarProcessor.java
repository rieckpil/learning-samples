package de.rieckpil.learning.control;

import de.rieckpil.learning.entity.Car;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import java.util.concurrent.Future;
import java.util.concurrent.locks.LockSupport;

@Stateless
@Asynchronous
public class CarProcessor {

    public void processNewCar(Car car) {
        LockSupport.parkNanos(2_000_000_000L);
    }

    public Future<String> calculateTax(Car car) {
        return new AsyncResult<String>("too expensive");
    }
}
