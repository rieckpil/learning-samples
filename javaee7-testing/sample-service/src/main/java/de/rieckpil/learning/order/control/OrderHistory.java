package de.rieckpil.learning.order.control;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.rieckpil.learning.order.entity.Order;

public class OrderHistory {

	@PersistenceContext
	EntityManager em;

	public void save(Order order) {
		this.em.merge(order);
	}
}
