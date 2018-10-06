package de.rieckpil.learning.order.boundary;

import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.rieckpil.learning.order.control.LegacyAuthenticator;
import de.rieckpil.learning.order.control.OrderHistory;
import de.rieckpil.learning.order.control.PaymentProcessor;
import de.rieckpil.learning.order.entity.Order;

@Stateless
public class OrderProcessor {

	@Inject
	LegacyAuthenticator authenticator;

	@Inject
	PaymentProcessor paymentProcessor;

	@Inject
	OrderHistory orderHistory;

	public void order() {
		if (!this.authenticator.authenticate()) {
			throw new IllegalStateException("not authenticated!");
		}

		this.paymentProcessor.pay();
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		this.orderHistory.save(order);
	}

}
