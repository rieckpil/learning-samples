package de.rieckpil.learning.springboot2bookmessaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.nio.charset.StandardCharsets;

import static de.rieckpil.learning.springboot2bookmessaging.PrepareMockSmtpListener.LATCH;
import static java.nio.charset.StandardCharsets.UTF_8;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.subethamail.smtp.server.SMTPServer;

@SpringBootApplication
public class SpringBoot2BookMessagingApplication implements CommandLineRunner {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringBoot2BookMessagingApplication.class, args);
        ((SMTPServer)ctx.getBean("smtpServer")).stop();
    }

    private final JavaMailSender mailSender;

    public SpringBoot2BookMessagingApplication(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void run(String... args) throws Exception {
        this.mailSender.send(mimeMessage -> {
            final MimeMessageHelper message =
                    new MimeMessageHelper(
                            mimeMessage, true, UTF_8.name()
                    );
            message.setFrom("hallo@springbootbuch.de");
            message.setTo("leser@springbootbuch.de");
            message.setSubject("Danke!");
            message.setText(
                    "Danke, dass Ihr mein Buch lest!",
                    "<html>"
                            + "<h1>Hallo!</h1>"
                            + "<p>Danke, dass ihr mein Buch lest!</p>"
                            + "</html>"
            );
        });
        LATCH.await();
    }
}
