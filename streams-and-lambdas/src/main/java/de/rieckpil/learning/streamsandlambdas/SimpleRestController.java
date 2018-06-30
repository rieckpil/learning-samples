package de.rieckpil.learning.streamsandlambdas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class SimpleRestController {

    @GetMapping
    public List<Person> getPersons() {
        List<Person> result = Arrays.asList(
                new Person("Tom", Gender.MALE, 20),
                new Person("Tim", Gender.MALE, 21),
                new Person("Jenny", Gender.FEMALE, 27),
                new Person("Anna", Gender.FEMALE, 41),
                new Person("Hans", Gender.MALE, 2),
                new Person("Tom", Gender.MALE, 81),
                new Person("Sarah", Gender.FEMALE, 35),
                new Person("Elisabeth", Gender.FEMALE, 66)
        );

        return result;
    }
}
