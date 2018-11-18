package de.rieckpil.learning.codingchallenges.problemsolving;

import java.util.Arrays;

public class ArrayLeftShift {

	public static void main(String[] args) {
		int[] input = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		System.out.println(Arrays.toString(rotLeft(input, 2)));

	}

	static int[] rotLeft(int[] a, int d) {

		int[] orgArray = new int[a.length];
		System.arraycopy(a, 0, orgArray, 0, a.length);

		for (int i = 0; i < a.length; i++) {
			int value = orgArray[i];
			int newIndexPosition = i - d;
			if (newIndexPosition < 0) {
				a[a.length + newIndexPosition] = value;
			} else {
				a[newIndexPosition] = value;
			}
		}

		return a;
	}

}
