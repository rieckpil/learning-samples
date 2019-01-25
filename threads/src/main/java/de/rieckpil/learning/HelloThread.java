package de.rieckpil.learning;

import java.util.concurrent.ThreadLocalRandom;

public class HelloThread extends Thread {

    @Override
    public void run() {

        try {
            long sleepDurationInMs = ThreadLocalRandom.current().nextLong(10_000);
            Thread.sleep(sleepDurationInMs);
            System.out.println(HelloThread.currentThread().getName() + ": Hello World! I slept for " + sleepDurationInMs + " ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
