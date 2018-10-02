package de.rieckpil.learning.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.rieckpil.learning.entity.Passport;

public interface PassportRepository extends JpaRepository<Passport, Long> {

}
