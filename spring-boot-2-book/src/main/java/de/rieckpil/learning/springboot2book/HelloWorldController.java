package de.rieckpil.learning.springboot2book;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String helloWorld(@RequestParam final String name) {
        return "Hello, " + name + "\n";
    }

}
