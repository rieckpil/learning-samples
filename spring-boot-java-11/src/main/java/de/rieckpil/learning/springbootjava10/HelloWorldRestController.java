package de.rieckpil.learning.springbootjava10;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloWorldRestController {

  private final PersonRepository personRepository;
  private final Dog dog;

  public HelloWorldRestController(PersonRepository personRepository, Dog dog) {
    this.personRepository = personRepository;
    this.dog = dog;
  }

  @GetMapping("/persons")
  private List<Person> getPersons() {

    // dog.setName(null);
    System.out.println(dog.getName());

    var persons = personRepository.findAll();

    return persons;
  }

  @GetMapping("/persons/{name}")
  private Person getPersonByName(@PathVariable("name") String name) {

    var person = personRepository.findByName(name);

    return person.orElseThrow(() -> new PersonNotFoundException());
  }
}
