package de.rieckpil.learning.sendgridexample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    private final MailSendingService mailSendingService;

    public MailController(MailSendingService mailSendingService) {
        this.mailSendingService = mailSendingService;
    }

    @GetMapping("/mails")
    public void  sendMail(@RequestParam("msg") String message) {
        mailSendingService.sendMail(message);
    }
}
