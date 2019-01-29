package de.rieckpil.learning;

public class NoVisibility {

	private static volatile boolean ready;
	private static int nr;

	private static class Reader extends Thread {

		@Override
		public void run() {
			while (!ready) {

			}
			System.out.println(nr);
		}
	}

	public static void main(String[] args) {

		new Reader().start();
		new Reader().start();
		new Reader().start();
		new Reader().start();

		try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}

		System.out.println("Finished sleeping");

		nr = 42;
		ready = true;

	}

}
