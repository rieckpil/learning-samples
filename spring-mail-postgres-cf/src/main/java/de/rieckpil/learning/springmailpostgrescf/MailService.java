package de.rieckpil.learning.springmailpostgrescf;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    /*private final MailSender mailSender;

    public MailService(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String message) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("mail@philipriecks.de");
        mailMessage.setFrom("noreply@springboot.de");
        mailMessage.setSubject("Hello World from Pivotal Web Services");
        mailMessage.setText(message);

        mailSender.send(mailMessage);

    }*/
}
