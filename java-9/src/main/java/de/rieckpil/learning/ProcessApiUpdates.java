package de.rieckpil.learning;

public class ProcessApiUpdates {

	public static void main(String[] args) {

		System.out.println(ProcessHandle.current().pid());
		System.out.println(ProcessHandle.current().info());
		System.out.println(ProcessHandle.current().info().user().get());
		System.out.println(ProcessHandle.current().info().command().get());
		System.out.println(ProcessHandle.current().info().totalCpuDuration().get());

		ProcessHandle.allProcesses().map(ProcessHandle::info).filter(p -> p.command().isPresent())
				.forEach(System.out::println);

	}

}
