package de.rieckpil.learning.codingchallenges.problemsolving;

public class HourGlassSum {
	
	/**
	 * 2D array of 6 x 6
	 * 
	 * 0 0 0 0 0 0
	 * 1 2 3 0 0 0
	 * 1 1 1 1 1 1
	 * 1 1 1 1 1 1
	 * 0 0 0 0 0 0
	 * 1 4 5 1 0 1
	 * 
	 * Hourglass looks like the following:
	 * 
	 * 1 1 1 
	 *   0  
	 * 1 2 3
	 * 
	 * @param arr, a 2D array of 6 x 6 
	 * @return the max sum of a existing hour glass
	 */
	static int hourglassSum(int[][] arr) {

		int maxHourGlassSum = Integer.MIN_VALUE;

		for (int i = 0; i < arr.length; i++) {

			if (i + 2 < arr.length) {
				for (int j = 0; j < arr[i].length; j++) {
					if (j + 2 < arr[i].length) {
						int hourGlassSumRow1 = arr[i][j] + arr[i][j + 1] + arr[i][j + 2];
						int hourGlassSumRow2 = arr[i + 1][j + 1];
						int hourGlassSumRow3 = arr[i + 2][j] + arr[i + 2][j + 1] + arr[i + 2][j + 2];
						int hourGlassSum = hourGlassSumRow1 + hourGlassSumRow2 + hourGlassSumRow3;
						if (hourGlassSum > maxHourGlassSum) {
							maxHourGlassSum = hourGlassSum;
						}
					}
				}
			}
		}
		return maxHourGlassSum;
	}

}
