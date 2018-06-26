package de.rieckpil.learning.springboot2junit5;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PersonFiller implements CommandLineRunner {

    private final PersonRepository personRepository;

    public PersonFiller(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Person p1 = new Person();
        p1.setFirstName("Philip");
        p1.setLastName("Riecks");
        p1.setDob(LocalDate.of(1995, 9, 13));

        Person p2 = new Person();
        p2.setFirstName("John");
        p2.setLastName("Doe");
        p2.setDob(LocalDate.of(2001, 9, 13));

        personRepository.save(p1);
        personRepository.save(p2);
    }
}
