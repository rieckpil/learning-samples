package de.rieckpil.learning.springbaeldungrest.boundary;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/hello")
public class HelloWorldResource {

    @GetMapping
    public String sayHello() {
        return "Hello World!";
    }
}
