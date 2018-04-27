package de.rieckpil.learning.jaxworkshop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloWorldController {

    @GetMapping
    public String sayHelloWorld() {
        return "Hello Jax!";
    }
}
