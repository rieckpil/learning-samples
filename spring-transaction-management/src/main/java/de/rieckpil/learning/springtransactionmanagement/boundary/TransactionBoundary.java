package de.rieckpil.learning.springtransactionmanagement.boundary;

import java.util.List;
import java.util.concurrent.locks.LockSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.rieckpil.learning.springtransactionmanagement.control.ProductRepository;
import de.rieckpil.learning.springtransactionmanagement.entity.Product;

@RestController("/transactions")
public class TransactionBoundary {

	@Autowired
	ProductRepository productRepository;

	@GetMapping
	@Transactional
	public List<Product> getAllProducts() {

		List<Product> products = productRepository.findAll();

		for (Product product : products) {
			product.setPrice(product.getPrice() * 2);
		}

		LockSupport.parkNanos(10_000_000_000L);

		return products;
	}

}
