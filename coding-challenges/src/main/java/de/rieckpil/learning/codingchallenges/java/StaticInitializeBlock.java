package de.rieckpil.learning.codingchallenges.java;

import java.util.Scanner;

public class StaticInitializeBlock {

	private static int B;
	private static int H;
	private static boolean flag = true;

	static {

		Scanner scan = new Scanner(System.in);

		B = scan.nextInt();
		H = scan.nextInt();

		if (H <= 0 || B <= 0) {
			flag = false;
			System.out.println("java.lang.Exception: Breadth and height must be positive");
		}

		scan.close();

	}

	public static void main(String[] args) {
		if (flag) {
			int area = B * H;
			System.out.print(area);
		}

	}
}
