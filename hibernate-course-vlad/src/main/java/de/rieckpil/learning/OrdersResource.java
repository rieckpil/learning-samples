package de.rieckpil.learning;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.rieckpil.learning.domain.Order;

@RestController
@RequestMapping("/orders")
public class OrdersResource {

	@Autowired
	private OrderRepository repository;

	@GetMapping
	@Transactional
	public List<Order> doFoo() {

		List<Order> orders = repository.findAll();
		Iterator<Order> iterator = orders.iterator();

		while (iterator.hasNext()) {
			Order order = iterator.next();
			order.setNumber(order.getNumber() + "X");
			order.getItems().get(0).setName("MANUAL");
		}

		repository.delete(orders.get(0));

		System.out.println(orders.size());
		List<Order> remainingOrders = repository.findAll();
		return remainingOrders;
	}

}
