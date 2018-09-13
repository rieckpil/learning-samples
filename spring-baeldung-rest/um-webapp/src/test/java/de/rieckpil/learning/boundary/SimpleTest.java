package de.rieckpil.learning.boundary;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

public class SimpleTest {

	@Test
	public void testAdd() {
		assertEquals(4, 2 + 2);
	}

	@Test
	@Ignore("Currently not supported")
	public void testSubstract() {
		assertEquals(4, 6 - 2);
	}
}
