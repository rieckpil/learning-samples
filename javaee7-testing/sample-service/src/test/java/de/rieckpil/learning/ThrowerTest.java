package de.rieckpil.learning;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ThrowerTest {

	private Thrower cut;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void init() {
		this.cut = new Thrower();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThrower() {
		this.cut.throwException();
	}

	@Test
	public void testThrower_detailed() {
		try {
			this.cut.throwException();
			fail();
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), containsString("illegal"));
		}
	}

	@Test
	public void testThrower_withRule() {
		this.thrown.expect(IllegalArgumentException.class);
		this.thrown.expectMessage("illegal");
		this.cut.throwException();
	}

}
