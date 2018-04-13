package de.rieckpil.learning.strings;

public class StringBuilderIntro {

	public static void main(String[] args) {

		StringBuilder sb = new StringBuilder(10);

		sb.append("12345678910");
		sb.append("12345678910");
		sb.append("12345678910");
		sb.append("12345678910");
		sb.append("12345678910");

		System.out.println(sb.toString());
		System.out.println(sb.capacity());
		System.out.println(sb.length());
		System.out.println(sb.deleteCharAt(sb.length() - 1));

	}

}
