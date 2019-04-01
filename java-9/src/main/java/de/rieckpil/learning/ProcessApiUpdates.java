package de.rieckpil.learning;

import java.io.IOException;

public class ProcessApiUpdates {

	public static void main(String[] args) throws IOException, InterruptedException {

		final String command = "cmd timeout 60";
		final Process sleeper = Runtime.getRuntime().exec(command);

		System.out.println(sleeper.pid());

		final ProcessHandle sleeperHandle = ProcessHandle.of(sleeper.pid()).orElseThrow(IllegalArgumentException::new);

		final Runnable exitHandler = () -> System.out.println("Process executed");
		sleeperHandle.onExit().thenRun(exitHandler);

		sleeperHandle.destroy();
		Thread.sleep(500);

//		printProcessInformation();

	}

	private static void printProcessInformation() {
		System.out.println(ProcessHandle.current().pid());
		System.out.println(ProcessHandle.current().info());
		System.out.println(ProcessHandle.current().info().user().get());
		System.out.println(ProcessHandle.current().info().command().get());
		System.out.println(ProcessHandle.current().info().totalCpuDuration().get());

		ProcessHandle.allProcesses().map(ProcessHandle::info).filter(p -> p.command().isPresent())
				.forEach(System.out::println);
	}

}
