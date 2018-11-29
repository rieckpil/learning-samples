package de.rieckpil.learning;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class PersonLoader implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public void run(String... args) throws Exception {

		if (personRepository.count() < 100_000) {

			System.out.println("Loading entries...");

			String[] firstnames = { "Tom", "Max", "Anna", "Hanna", "Mike", "Duke", "Fred", "Tim", "Paul", "Michaela",
					"Tobias", "Irmgard", "Michelle", "Thomas", "Andrew" };
			String[] lastnames = { "Müller", "Meier", "Schulz", "Hammer", "Schuster", "Pfister", "Schmidt", "Baier",
					"Mayer", "Schmid" };

			LocalDateTime initDate = LocalDateTime.of(1995, 9, 13, 12, 12);

			for (int i = 0; i < 94993; i++) {

				Person p = new Person();
				p.setBudget(ThreadLocalRandom.current().nextInt(100000));
				p.setDob(Instant.ofEpochSecond(initDate.plusDays(i).toEpochSecond(ZoneOffset.UTC)));
				p.setFirstname(firstnames[ThreadLocalRandom.current().nextInt(0, firstnames.length)]);
				p.setLastname(lastnames[ThreadLocalRandom.current().nextInt(0, lastnames.length)]);

				personRepository.save(p);

			}
		} else {
			System.out.println("Not loading entries due to already filled database...");

		}

	}

}
