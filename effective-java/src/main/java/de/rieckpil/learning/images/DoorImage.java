package de.rieckpil.learning.images;

public class DoorImage {

	// @formatter:off
	public static void main(String[] args) {

		/** Create following door image with nested for-loops:
		 * 
		 * ############
		 * ##        ##
		 * # #      # #
		 * #  #    #  #
		 * #   #  #   #   
		 * #    ##    #
		 * #    ##    #
		 * #   #  #   #
		 * #  #    #  #
		 * # #      # #
		 * ##        ## 
		 * ############
		 * 
		 */

		draw();
	}
	// @formatter:on

	private static void draw() {

		int spaces = 0;
		int gap = 8;

		spaces = gap;
		gap = spaces;

		System.out.println("#############");

		for (int i = 0; i < 12; i++) {
			System.out.println("");
		}

		System.out.println("#############");

	}

}
