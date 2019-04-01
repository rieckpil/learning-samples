package de.rieckpil.learning;

import java.awt.Taskbar;
import java.awt.Taskbar.Feature;
import java.util.Arrays;

public class TaskBarSupport {

	public static void main(String[] args) {
		System.out.println(Taskbar.isTaskbarSupported());

		Taskbar taskbar = Taskbar.getTaskbar();

		Arrays.stream(Feature.values())
				.forEach(feature -> System.out.println(feature + " is supported: " + taskbar.isSupported(feature)));
		
	}

}
