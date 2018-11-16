package de.rieckpil.learning.codingchallenges.java;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class BigDecimalSorting {

	public static void main(String[] args) {

		String[] numbers = { "0.00", "1.23", "-80", "100", "10000", ".81" };

		Arrays.sort(numbers, Collections.reverseOrder(new Comparator<String>() {
			@Override
			public int compare(String a1, String a2) {
				if (a1 == null)
					return -1;
				if (a2 == null)
					return 1;
				BigDecimal a = new BigDecimal(a1);
				BigDecimal b = new BigDecimal(a2);
				return a.compareTo(b);

			}
		}));

		for (String string : numbers) {
			System.out.println(string);
		}

	}
}
