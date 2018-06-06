package de.rieckpil.learning.herokuspringbootsample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonsBoundary {

    private final PersonRepository personRepository;


    public PersonsBoundary(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/persons")
    public List<Person> getAllPersons() {

        return personRepository.findAll();

    }

    @GetMapping("/persons/{name}")
    public Person createNewPerson(@PathVariable("name") String name) {

        Person newPerson = new Person();
        newPerson.setName(name);

        personRepository.save(newPerson);

        return newPerson;
    }
}
