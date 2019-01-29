package de.rieckpil.learning;

import java.util.concurrent.ThreadLocalRandom;

public class HelloThread extends Thread {

	private Counter counter;

	public HelloThread(Counter counter) {
		this.counter = counter;
	}

	@Override
	public void run() {

		try {
			System.out.println(counter.getNext());
			long sleepDurationInMs = ThreadLocalRandom.current().nextLong(1_000);
			Thread.sleep(sleepDurationInMs);
			System.out.println(
					HelloThread.currentThread().getName() + ": Hello World! I slept for " + sleepDurationInMs + " ms");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
