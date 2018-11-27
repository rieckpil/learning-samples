package de.rieckpil.learning.codingchallenges.java;

import java.security.MessageDigest;
import java.util.Scanner;

public class SHA256HashingSample {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String n = scan.next();

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(n.getBytes());
			byte[] hash = messageDigest.digest();
			for (byte b : hash) {
				System.out.printf("%02x", b);
			}
		} catch (Exception e) {
		}
		scan.close();
	}
}