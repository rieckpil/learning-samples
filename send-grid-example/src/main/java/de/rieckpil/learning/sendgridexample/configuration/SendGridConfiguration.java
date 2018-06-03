package de.rieckpil.learning.sendgridexample.configuration;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendGridConfiguration {

    @Bean
    public SendGrid sendGrid(@Value("${sendgrid.api.key}") String apiKey) {
        return new SendGrid(apiKey);
    }
}
