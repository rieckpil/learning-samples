package de.rieckpil.learning.springtransactionmanagement.control;

import de.rieckpil.learning.springtransactionmanagement.entity.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class ProductLoader implements CommandLineRunner {

    private final ProductRepository productRepository;

    public ProductLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Loading new products");

        Product p1 = new Product("Laptop", UUID.randomUUID().toString(), 42.15);

        productRepository.save(p1);

    }
}
