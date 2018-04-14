package de.rieckpil.learning.collections;

import java.util.List;

public class Java9Collections {

	public static void main(String[] args) {

		List<String> immutableList = List.of("Hello", "World", "Duke");

		for (String listItem : immutableList) {
			System.out.println(listItem);
		}

		// immutableList.add("Franz"); -> throws UnsupportedOperationException
	}

}
