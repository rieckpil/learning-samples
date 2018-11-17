package de.rieckpil.learning.codingchallenges.java;

public class GetSmallestAndLargestString {

	public static String getSmallestAndLargest(String s, int k) {
		String smallest = "";
		String largest = "";

		smallest = s.substring(0, k);
		largest = s.substring(0, k);

		for (int i = 0; i < s.length(); i++) {
			if (i + k <= s.length()) {
				String string = s.substring(i, i + k);
				if (string.compareTo(smallest) < 0) {
					smallest = string;
				}

				if (string.compareTo(largest) > 0) {
					largest = string;
				}
			}
		}

		return smallest + "\n" + largest;
	}

	public static void main(String[] args) {
		System.out.println(getSmallestAndLargest("welcometojava", 3));
	}

}
