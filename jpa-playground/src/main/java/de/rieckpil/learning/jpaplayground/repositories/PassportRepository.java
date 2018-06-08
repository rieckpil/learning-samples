package de.rieckpil.learning.jpaplayground.repositories;

import de.rieckpil.learning.jpaplayground.entities.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepository extends JpaRepository<Passport, Long> {
}
