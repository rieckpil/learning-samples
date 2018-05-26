package de.rieckpil.learning.springboot2bookmessaging;

import static java.nio.charset.StandardCharsets.UTF_8;
import static de.rieckpil.learning.springboot2bookmessaging.PrepareMockSmtpListener.LATCH;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    private final JmsTemplate jmsTemplate;

    public GreetingService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    void sendGreeting(final String greeting) {

        this.jmsTemplate.send("greetings-topic", session -> session.createTextMessage(greeting));

    }
}
