package de.rieckpil.learning.codingchallenges.java;

import java.util.Scanner;

public class StringPalindromeChecker {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String A = sc.next();
		if (new StringBuffer(A).reverse().toString().equals(A)) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}

		sc.close();
	}

}
