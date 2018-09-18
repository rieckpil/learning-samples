package de.rieckpil.learning;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import de.rieckpil.learning.jdbc.PersonJdbcRepository;

@Component
public class Runner implements CommandLineRunner {

	@Autowired
	private PersonJdbcRepository repository;

	@Override
	public void run(String... args) throws Exception {

		List<Person> result = repository.findAll();

		for (Person person : result) {
			System.out.println(person);
		}

		Person p1 = repository.findById(1);

		System.out.println("#1 == " + p1);

		System.out.println(repository.deleteById(1));
		System.out.println(repository.insert(new Person(1848, "Paul", "Bochum", LocalDateTime.now())));
		System.out.println("#1848 == " + repository.findById(1848));

	}

}