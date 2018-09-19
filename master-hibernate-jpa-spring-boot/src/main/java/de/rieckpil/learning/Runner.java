package de.rieckpil.learning;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import de.rieckpil.learning.jdbc.PersonJdbcRepository;
import de.rieckpil.learning.jpa.PersonJpaRepository;

@Component
@Profile("jdbc")
public class Runner implements CommandLineRunner {

	@Autowired
	private PersonJdbcRepository jdbcRepository;

	@Autowired
	private PersonJpaRepository jpaRepository;

	@Override
	public void run(String... args) throws Exception {
		jpaRepository.insert(new Person("Malik", "Darmstadt", LocalDateTime.now()));
		jpaRepository.insert(new Person("Jörg", "Darmstadt", LocalDateTime.now()));
		jpaRepository.insert(new Person("Armin", "Mannheim", LocalDateTime.now()));
		jpaRepository.update(new Person(1001, "rieckpil", "Darmstadt", LocalDateTime.now()));
		jpaRepository.deleteById(1002);

		List<Person> allPersons = jpaRepository.findAll();

		for (Person person : allPersons) {
			System.out.println(person);
		}

	}

	private void doJdbcStuff() {
		List<Person> result = jdbcRepository.findAll();

		for (Person person : result) {
			System.out.println(person);
		}

		System.out.println("#1 == " + jdbcRepository.findById(1001));
		System.out.println(jdbcRepository.deleteById(1001));
		System.out.println(jdbcRepository.insert(new Person(1848, "Paul", "Bochum", LocalDateTime.now())));
		System.out.println("#1848 == " + jdbcRepository.findById(1848));
	}

}
