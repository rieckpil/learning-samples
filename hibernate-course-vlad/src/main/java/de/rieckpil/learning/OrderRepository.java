package de.rieckpil.learning;

import org.springframework.data.jpa.repository.JpaRepository;

import de.rieckpil.learning.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
