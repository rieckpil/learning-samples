package de.rieckpil.learning.jpaplayground.repositories;

import de.rieckpil.learning.jpaplayground.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
