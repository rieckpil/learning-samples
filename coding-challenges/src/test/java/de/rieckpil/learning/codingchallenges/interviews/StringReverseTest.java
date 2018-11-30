package de.rieckpil.learning.codingchallenges.interviews;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringReverseTest {

	@Test
	public void testReverseTwo() {
		// define

		// act
		String input = "Test";
		String result = StringReverse.reverseTwo(input);

		// assert
		assertEquals(new StringBuilder(input).reverse().toString(), result);
	}
	
	@Test
	public void testReverseTwoWithEmptyInputShouldBeEmpty() {
		// define

		// act
		String input = "";
		String result = StringReverse.reverseTwo(input);

		// assert
		assertEquals(new StringBuilder(input).reverse().toString(), result);
	}
	
	@Test
	public void testReverseTwoWithNumbersInputShouldContainNumbersAndBeReverse() {
		// define

		// act
		String input = "Test1234";
		String result = StringReverse.reverseTwo(input);

		// assert
		assertEquals(new StringBuilder(input).reverse().toString(), result);
	}
	
	@Test
	public void testReverseTwoWithCaseSensitiveInputShouldStayCaseSensitive() {
		// define

		// act
		String input = "sTlksSÖÖÖLLLoos";
		String result = StringReverse.reverseTwo(input);

		// assert
		assertEquals(new StringBuilder(input).reverse().toString(), result);
	}



}
