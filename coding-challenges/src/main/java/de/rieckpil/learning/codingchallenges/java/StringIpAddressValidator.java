package de.rieckpil.learning.codingchallenges.java;

import java.util.Scanner;

public class StringIpAddressValidator {
	private static final String ZERO_TO_255 = "(\\d{1,2}|(0|1)\\d{2}|2[0-4]\\d|25[0-5])";
	private static final String PATTERN = ZERO_TO_255 + "." + ZERO_TO_255 + "." + ZERO_TO_255 + "." + ZERO_TO_255;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String IP = in.next();
			System.out.println(IP.matches(PATTERN));
		}
		in.close();
	}
}
