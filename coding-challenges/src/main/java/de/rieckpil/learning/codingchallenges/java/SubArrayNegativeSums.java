package de.rieckpil.learning.codingchallenges.java;

import java.util.Scanner;

public class SubArrayNegativeSums {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		int[] arr = new int[n];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = scan.nextInt();
		}

		int amountOfNegativeSubArrays = 0;

		for (int i = 0; i < n; i++) {
			int sum = 0;
			for (int j = i; j < n; j++) {
				sum = sum + arr[j];
				if (sum < 0)
					amountOfNegativeSubArrays++;
			}
		}

		System.out.println(amountOfNegativeSubArrays);
		scan.close();
	}
}
