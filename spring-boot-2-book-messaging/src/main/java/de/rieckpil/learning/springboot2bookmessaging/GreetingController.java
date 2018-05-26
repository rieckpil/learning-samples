package de.rieckpil.learning.springboot2bookmessaging;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/greeting")
    public void greet(@RequestParam("msg") String message) {
        this.greetingService.sendGreeting(message);
    }
}
