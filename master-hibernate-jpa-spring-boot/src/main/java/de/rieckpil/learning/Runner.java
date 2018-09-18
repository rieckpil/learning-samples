package de.rieckpil.learning;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import de.rieckpil.learning.jdbc.PersonJdbcRepository;
import de.rieckpil.learning.jpa.PersonJpaRepository;

@Component
public class Runner implements CommandLineRunner {

	@Autowired
	private PersonJdbcRepository jdbcRepository;
	
	@Autowired
	private PersonJpaRepository jpaRepository;

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("#1 == " + jpaRepository.findById(1));

	}

	private void doJdbcStuff() {
		List<Person> result = jdbcRepository.findAll();

		for (Person person : result) {
			System.out.println(person);
		}

		System.out.println("#1 == " + jdbcRepository.findById(1));
		System.out.println(jdbcRepository.deleteById(1));
		System.out.println(jdbcRepository.insert(new Person(1848, "Paul", "Bochum", LocalDateTime.now())));
		System.out.println("#1848 == " + jdbcRepository.findById(1848));
	}

}
