package de.rieckpil.learning.springtransactionmanagement.control;

import de.rieckpil.learning.springtransactionmanagement.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RareProductLoader {

    private final ProductRepository productRepository;

    public RareProductLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = ProductAvailabiltyException.class)
    public void storeRareProduct() throws ProductAvailabiltyException {
        Product rareProduct = new Product("Laptop", UUID.randomUUID().toString(), 42.15);
        productRepository.save(rareProduct);
        throw new ProductAvailabiltyException("Rare product is not available");
    }
}
