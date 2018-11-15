package de.rieckpil.learning.codingchallenges.java;

import java.util.Scanner;

public class SplitSentenceIntoTokens {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String s = scan.nextLine();

		s = s.trim();

		if (s == null || s.equals("")) {
			System.out.println("0");
			scan.close();
			return;
		}
		String[] tokens = s.trim().split("[ !,?._'@]+");
		System.out.println(tokens.length);
		for (String token : tokens) {
			System.out.println(token);
		}
		scan.close();
	}
}
