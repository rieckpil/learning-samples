package de.rieckpil.learning.testcontainerssample;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonBoundary {

    private final PersonRepository personRepository;

    public PersonBoundary(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getAllPersons() {
        return new ResponseEntity<>(personRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(personRepository.findById(id).orElseThrow(() -> new NoPersonFoundException
                ()), HttpStatus.OK);
    }
 }
