package de.rieckpil.learning.codingchallenges.java;

public class StringAnagramChecker {

	static boolean isAnagram(String a, String b) {

		char[] charArrayA = a.toUpperCase().toCharArray();
		java.util.Arrays.sort(charArrayA);
		String sortedStringA = new String(charArrayA);

		char[] charArrayB = b.toUpperCase().toCharArray();
		java.util.Arrays.sort(charArrayB);
		String sortedStringB = new String(charArrayB);

		if (sortedStringA.equals(sortedStringB)) {
			return true;
		} else {
			return false;
		}

	}

}
