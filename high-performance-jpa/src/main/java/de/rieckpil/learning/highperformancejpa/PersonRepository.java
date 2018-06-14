package de.rieckpil.learning.highperformancejpa;

import de.rieckpil.learning.highperformancejpa.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
