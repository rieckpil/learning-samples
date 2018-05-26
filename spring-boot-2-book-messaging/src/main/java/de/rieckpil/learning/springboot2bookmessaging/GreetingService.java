package de.rieckpil.learning.springboot2bookmessaging;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    private final JmsTemplate jmsTemplate;

    public GreetingService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendGreeting(final String greeting) {

        this.jmsTemplate.send("greetings-topic", session -> session.createTextMessage(greeting));

    }
}
