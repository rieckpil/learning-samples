package de.rieckpil.learning.jpaplayground.repositories;

import de.rieckpil.learning.jpaplayground.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {


}
