package de.rieckpil.learning;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface PersonRepository
		extends JpaRepository<Person, Long>, QueryByExampleExecutor<Person>, JpaSpecificationExecutor<Person> {

}
