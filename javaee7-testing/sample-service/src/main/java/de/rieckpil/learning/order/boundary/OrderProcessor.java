package de.rieckpil.learning.order.boundary;

import de.rieckpil.learning.order.control.LegacyAuthenticator;
import de.rieckpil.learning.order.control.PaymentProcessor;

public class OrderProcessor {

	LegacyAuthenticator authenticator;
	PaymentProcessor paymentProcessor;

	public OrderProcessor() {
		this.authenticator = new LegacyAuthenticator();
		this.paymentProcessor = new PaymentProcessor();
	}

	public void order() {
		if (!this.authenticator.authenticate()) {
			throw new IllegalStateException("not authenticated!");
		}

		paymentProcessor.pay();
	}

}
