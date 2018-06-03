package de.rieckpil.learning.sendgridexample;

import com.sendgrid.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MailSendingService {

    private SendGrid sendGridClient;

    public MailSendingService(SendGrid sendGridClient) {
        this.sendGridClient = sendGridClient;
    }

    public void sendMail(String message) {

        Response result = this.sendEmail("hello@sendgrid.io", "mail@philipriecks.de", "Hello World Send Grid!", new
                Content("text", message));

    }

    private Response sendEmail(String from, String to, String subject, Content content) {
        Mail mail = new Mail(new Email(from), subject, new Email(to), content);
        mail.setReplyTo(new Email("mail@philipriecks.de"));
        Request request = new Request();
        Response response = null;
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            this.sendGridClient.api(request);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return response;
    }
}
