package de.rieckpil.learning.order.boundary;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import de.rieckpil.learning.order.control.LegacyAuthenticator;

public class OrderProcessorTest {

	private OrderProcessor cut;

	@Before
	public void init() {
		this.cut = new OrderProcessor();

	}

	@Test
	public void testSuccessfulOrder() {
		this.cut.order();
	}

	@Test(expected = IllegalStateException.class)
	public void testInvalidUser() {
		this.cut.authenticator = mock(LegacyAuthenticator.class);
		when(this.cut.authenticator.authenticate()).thenReturn(false);
		this.cut.order();
	}

}
