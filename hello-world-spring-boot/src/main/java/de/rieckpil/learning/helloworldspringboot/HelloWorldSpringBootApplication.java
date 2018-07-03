package de.rieckpil.learning.helloworldspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sun.tools.jar.CommandLine;

import java.util.UUID;

@SpringBootApplication
public class HelloWorldSpringBootApplication {

    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldSpringBootApplication.class, args);
    }

    @Bean
    CommandLineRunner runner () {
        return args -> {
            personRepository.save(new Person(null, "Philip", "Riecks", UUID.randomUUID().toString()));
            personRepository.save(new Person(null, "John", "Doe", UUID.randomUUID().toString()));
            personRepository.save(new Person(null, "Max", "Mustermann", UUID.randomUUID().toString()));
            personRepository.save(new Person(null, "Maximilia", "Musterfrau", UUID.randomUUID().toString()));
        };
    }
}
