package de.rieckpil.learning.springboot2junit5;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

@Controller
public class WebSocketController {

    private final PersonRepository personRepository;

    public WebSocketController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @SubscribeMapping("/hello")
    public List<String> sayHello() {
        System.out.println("Incoming subscription for /hello at: " + LocalDateTime.now(ZoneId.of("Europe/Berlin")));
        return Arrays.asList("Hello", "World", "!");
    }

    @SubscribeMapping("/hello/{username}")
    public List<String> sayHelloToUser(@DestinationVariable String username) {
        System.out.println("Incoming subscription for /hello/{username} at: " + LocalDateTime.now(ZoneId.of("Europe/Berlin")));
        return Arrays.asList("Hello", "World", username, "!");
    }

    @SubscribeMapping("/persons")
    public List<Person> getAllPersons() {
        System.out.println("Incoming subscription for /persons at: " + LocalDateTime.now(ZoneId.of("Europe/Berlin")));
        return personRepository.findAll();
    }
}
