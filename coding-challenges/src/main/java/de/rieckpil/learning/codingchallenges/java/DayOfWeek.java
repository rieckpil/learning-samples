package de.rieckpil.learning.codingchallenges.java;

import java.text.DateFormatSymbols;
import java.util.Calendar;

public class DayOfWeek {

	public static String findDay(int month, int day, int year) {
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, day);

		DateFormatSymbols dfs = new DateFormatSymbols();

		return dfs.getWeekdays()[cal.get(Calendar.DAY_OF_WEEK)].toUpperCase();
	}

}
