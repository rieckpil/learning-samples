package de.rieckpil.learning.springmailpostgrescf;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final MailService mailService;
    private final MessageRepository messageRepository;

    public MessageController(MailService mailService, MessageRepository messageRepository) {
        this.mailService = mailService;
        this.messageRepository = messageRepository;
    }

    @GetMapping("/messages")
    public void sendNewMessage(@RequestParam(value = "msg", required = true) String message) {

        Message msg = new Message();
        msg.setMessage(message);

        messageRepository.save(msg);
        // mailService.sendMail(message);

    }
}
