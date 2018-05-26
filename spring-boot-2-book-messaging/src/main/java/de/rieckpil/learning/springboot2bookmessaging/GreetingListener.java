package de.rieckpil.learning.springboot2bookmessaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GreetingListener {

    @JmsListener(destination = "greetings-topic")
    @SendTo("responses-queue")
    public String onGreeting(final String greeting) {

        log.info("Received greeting: '{}'", greeting);
        return "Hello from Spring!";

    }

}
