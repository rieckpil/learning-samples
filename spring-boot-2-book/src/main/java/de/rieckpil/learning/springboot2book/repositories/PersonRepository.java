package de.rieckpil.learning.springboot2book.repositories;

import de.rieckpil.learning.springboot2book.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
