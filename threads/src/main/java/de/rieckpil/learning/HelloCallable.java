package de.rieckpil.learning;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class HelloCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        long sleepDurationInMs = ThreadLocalRandom.current().nextLong(1_000);
        Thread.sleep(sleepDurationInMs);
        return UUID.randomUUID().toString();
    }
}
