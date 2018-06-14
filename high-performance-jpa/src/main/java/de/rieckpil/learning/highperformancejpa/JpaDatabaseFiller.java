package de.rieckpil.learning.highperformancejpa;

import de.rieckpil.learning.highperformancejpa.entity.Person;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JpaDatabaseFiller implements CommandLineRunner {

    private final PersonRepository personRepository;

    public JpaDatabaseFiller(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Person p1 = new Person();
        p1.setName("Tommy");

        personRepository.save(p1);

    }
}
