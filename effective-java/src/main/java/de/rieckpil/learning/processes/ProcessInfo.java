package de.rieckpil.learning.processes;

public class ProcessInfo {

	public static void main(String[] args) {
		ProcessHandle currentProcess = ProcessHandle.current();
		System.out.println("Current Process Id: = " + currentProcess.pid());
	}
}
