package de.rieckpil.learning.springtransactionmanagement.control;

import de.rieckpil.learning.springtransactionmanagement.entity.Product;

import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.LockSupport;

@Service
public class ProductLoader implements CommandLineRunner {

	private final ProductRepository productRepository;
	private final RareProductLoader rareProductLoader;
	private final TransactionTemplate transactionTemplate;

	public ProductLoader(ProductRepository productRepository, RareProductLoader rareProductLoader,
			TransactionTemplate transactionTemplate) {
		this.productRepository = productRepository;
		this.rareProductLoader = rareProductLoader;
		this.transactionTemplate = transactionTemplate;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Loading new products ...");

		transactionTemplate.execute(new TransactionCallbackWithoutResult() {

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				Product p1 = new Product("Laptop1", UUID.randomUUID().toString(),
						ThreadLocalRandom.current().nextLong(0, 100));
				Product p2 = new Product("Laptop2", UUID.randomUUID().toString(),
						ThreadLocalRandom.current().nextLong(0, 100));

				productRepository.save(p1);
				productRepository.save(p2);
			}
		});

		transactionTemplate.execute(T -> {
			Product p1 = new Product("Laptop3", UUID.randomUUID().toString(),
					ThreadLocalRandom.current().nextLong(0, 100));
			Product p2 = new Product("Laptop4", UUID.randomUUID().toString(),
					ThreadLocalRandom.current().nextLong(0, 100));

			productRepository.save(p1);
			productRepository.save(p2);
			return null;
		});

		Product p1 = new Product("Laptop5", UUID.randomUUID().toString(), ThreadLocalRandom.current().nextLong(0, 100));
		Product p2 = new Product("Laptop6", UUID.randomUUID().toString(), ThreadLocalRandom.current().nextLong(0, 100));
		Product p3 = new Product("Laptop7", UUID.randomUUID().toString(), ThreadLocalRandom.current().nextLong(0, 100));

		productRepository.save(p1);

		LockSupport.parkNanos(1_000_000_000L);

		productRepository.save(p2);
		productRepository.save(p3);

		System.out.println("Let's store a really rare Product!");

		try {
			rareProductLoader.storeRareProduct();
		} catch (ProductAvailabiltyException e) {
			System.out.println("Could not store the rare product!");
		}

	}
}
