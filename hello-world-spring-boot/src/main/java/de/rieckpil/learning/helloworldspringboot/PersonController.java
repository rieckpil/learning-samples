package de.rieckpil.learning.helloworldspringboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!";
    }

    @GetMapping("/persons")
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    @GetMapping("/persons/{id}")
    public Person getPersonById(@PathVariable("id") Long id) {

        return personRepository.findAll().stream()
                .filter(e -> e.getId().longValue() == id.longValue())
                .findFirst()
                .orElseThrow(() -> new PersonNotFoundException("No person found with id: " + id));

    }
}
