package de.rieckpil.learning.ping.control;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.concurrent.Future;

@Stateless
public class PushMultiplier {

    @Inject
    @ComputationResults
    Event<Long> results;

    @Asynchronous
    public void multiply(long a, long b) {
        long c = a * b;
        results.fire(c);
    }
}
