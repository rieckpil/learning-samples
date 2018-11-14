package de.rieckpil.learning.codingchallenges.problemsolving;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CountingValleys {

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int n = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		String s = scanner.nextLine();

		int result = countingValleys(n, s);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedWriter.close();

		scanner.close();
	}

	static int countingValleys(int n, String s) {

		int result = 0;
		int altitude = 0;
		int lastAltitude = 0;

		for (String walk : s.split("")) {

			lastAltitude = altitude;

			if (walk.equals("D")) {
				altitude--;
			} else {
				altitude++;
			}

			if (lastAltitude < 0 && altitude == 0) {
				result++;
			}
		}

		return result;
	}

}
