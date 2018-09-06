package de.rieckpil.learning.springtransactionmanagement.control;

import de.rieckpil.learning.springtransactionmanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
