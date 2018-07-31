package de.rieckpil.learning.ping.control;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PushParallelizer {

    @Inject
    PushMultiplier asyncWorker;

    public void executeInParallel(int iterations) {

        for (int i = 0; i < iterations; i++) {
            asyncWorker.multiply(i, i);
        }

    }
}
