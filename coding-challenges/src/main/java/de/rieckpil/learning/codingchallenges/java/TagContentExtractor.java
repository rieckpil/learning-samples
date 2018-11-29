package de.rieckpil.learning.codingchallenges.java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagContentExtractor {

	public static void main(String[] args) {

		String[] input = { "<h1>Nayeem loves counseling</h1>",
				"<h1><h1>Sanjay has no watch</h1></h1><par>So wait for a while</par>",
				"<Amee>safat codes like a ninja</amee>", "<SA premium>Imtiaz has a secret crush</SA premium>" };

		int testCases = 0;

		while (testCases < input.length) {
			String line = input[testCases];

			boolean matchFound = false;
			Pattern r = Pattern.compile("<(.+)>([^<]+)</\\1>");
			Matcher m = r.matcher(line);

			while (m.find()) {
				System.out.println(m.group(2));
				matchFound = true;
			}
			if (!matchFound) {
				System.out.println("None");
			}

			testCases++;
		}
	}

}
