package de.rieckpil.learning.codingchallenges.interviews;

public class StringReverse {

	public static void main(String[] args) {
		System.out.println(reverseTwo("Mike"));
		System.out.println("Test");

		final String a = "X";
		// a = "B"; // won't work due to final
		System.out.println(a);

	}

	public static String reverse(String s) {
		return new StringBuilder(s).reverse().toString();
	}

	public static String reverseTwo(String s) {
		char[] chars = s.toCharArray();

		String result = "";

		for (int i = chars.length - 1; i >= 0; i--) {
			result = new String(result + Character.toString(chars[i]));
		}

		return result;
	}

}
