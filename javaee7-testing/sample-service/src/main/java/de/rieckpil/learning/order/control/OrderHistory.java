package de.rieckpil.learning.order.control;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.rieckpil.learning.order.entity.Order;

public class OrderHistory {

	@PersistenceContext(unitName = "prod")
	EntityManager em;

	public void save(Order order) {
		this.em.merge(order);
	}

	public List<Order> getAllOrders() {
		return em.createQuery("SELECT o FROM Order o", Order.class).getResultList();
	}
}
