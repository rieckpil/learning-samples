package de.rieckpil.learning;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class SpringDataJpaProjectionApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaProjectionApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		userRepository.save(new User("John", "Doe", LocalDate.now(), UUID.randomUUID().toString()));
		userRepository.save(new User("Foo", "Bar", LocalDate.now(), UUID.randomUUID().toString()));
		userRepository.save(new User("Duke", "Java", LocalDate.now(), UUID.randomUUID().toString()));
		userRepository.save(new User("Tom", "Saywer", LocalDate.now(), UUID.randomUUID().toString()));

		System.out.println(userRepository.count());

		List<NamesOnly> result = userRepository.findByLastName("Java");

		System.out.println(result.get(0).getFullName());

	}
}
