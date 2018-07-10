package de.rieckpil.learning.jasperintro;

import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class JasperIntroApplication {

    @Autowired
    private PerformanceTester performanceTester;

    @Autowired
    private SendGrid sendGrid;
    
    @Value("${templateId}")
    private String EMAIL_TEMPLATE_ID;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {

            String pdfOutputFileName = "/Users/Philip/Desktop/junk/jasper/performance/report";

            long start = System.currentTimeMillis();

            for (int i = 0; i < 1; i++) {
                byte[] jasperReport = performanceTester.createJasperReport(pdfOutputFileName + i + ".pdf",
                        100);
                
                sendMail(jasperReport);
            }

            System.out.println("took: " + ((System.currentTimeMillis() - start) / 1000) + " seconds");

        };
    }

    private void sendMail(byte[] jasperReport) {

        Email from = new Email("mail@philipriecks.de");
        String subject = "Hello World!";
        Email to = new Email("mail@philipriecks.de");
        Content content = new Content("text/html", "I'm replacing the <strong>body tag</strong>");
        Mail mail = new Mail(from, subject, to, content);
        mail.setReplyTo(new Email("mail@philipriecks.de"));
        mail.personalization.get(0).addSubstitution("-username-", "Some blog user");
        mail.setTemplateId(EMAIL_TEMPLATE_ID);
        mail.attachments.add(new Attachments.Builder(subject, new ByteArrayInputStream(jasperReport)).build());
        Request request = new Request();
        Response response = null;
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            response = sendGrid.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(JasperIntroApplication.class, args);
    }
}
