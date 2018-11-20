package de.rieckpil.learning.codingchallenges.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MapPhoneBook {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Map<String, Integer> phoneBook = new HashMap<>();
		int n = in.nextInt();
		in.nextLine();
		for (int i = 0; i < n; i++) {
			String name = in.nextLine();
			int phone = in.nextInt();
			phoneBook.put(name, phone);
			in.nextLine();
		}

		while (in.hasNext()) {
			String s = in.nextLine();

			if (phoneBook.get(s) != null) {
				System.out.println(s + "=" + phoneBook.get(s));
			} else {
				System.out.println("Not found");
			}

		}
		in.close();
	}
}
