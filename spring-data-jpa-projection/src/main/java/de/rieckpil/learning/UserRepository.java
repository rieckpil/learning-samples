package de.rieckpil.learning;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	List<NamesOnly> findByLastName(String lastName);

}
