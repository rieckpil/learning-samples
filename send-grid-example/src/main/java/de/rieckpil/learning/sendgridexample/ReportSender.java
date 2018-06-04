package de.rieckpil.learning.sendgridexample;

import com.sendgrid.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ReportSender implements CommandLineRunner {

    private static final String EMAIL_TEAMPLTE_ID = "68010d79-91c2-47f9-b8ab-3330ddbfa935";

    private final SendGrid sendGrid;

    public ReportSender(SendGrid sendGrid) {
        this.sendGrid = sendGrid;
    }

    @Override
    public void run(String... args) throws Exception {

        Email from = new Email("mail@philipriecks.de");
        String subject = "Hello World!";
        Email to = new Email("mail@philipriecks.de");
        Content content = new Content("text/html", "I'm replacing the <strong>body tag</strong>");
        Mail mail = new Mail(from, subject, to, content);
        mail.personalization.get(0).addSubstitution("-user-", "Example User");
        mail.personalization.get(0).addSubstitution("-password-", "1234");
        mail.setTemplateId(EMAIL_TEAMPLTE_ID);

        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }

    }


    public void getAllTemplates() {
        try {
            Request request = new Request();
            request.setMethod(Method.GET);
            request.setEndpoint("templates");
            Response response = sendGrid.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
