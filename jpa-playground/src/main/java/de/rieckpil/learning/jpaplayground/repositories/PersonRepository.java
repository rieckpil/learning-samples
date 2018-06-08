package de.rieckpil.learning.jpaplayground.repositories;

import de.rieckpil.learning.jpaplayground.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface PersonRepository extends RevisionRepository<Person, Long, Integer>, JpaRepository<Person, Long> {


}
