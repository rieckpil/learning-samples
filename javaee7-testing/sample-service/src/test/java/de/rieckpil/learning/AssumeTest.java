package de.rieckpil.learning;

import static org.junit.Assert.fail;
import static org.junit.Assume.assumeNoException;

import org.junit.Test;

public class AssumeTest {

	@Test
	public void assumeNoExceptionDemonstration() {
		try {
			throw new IllegalStateException("Internet not available today");
		} catch (Exception e) {
			assumeNoException(e);
		}

		fail("should not run");
	}

}
