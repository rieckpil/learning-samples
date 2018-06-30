package de.rieckpil;

import com.sendgrid.SendGrid;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

public class SendGridConfiguration {

    @Inject
    @ConfigProperty(name = "send_grid_api_key")
    private String apiKey;

    @Produces
    public SendGrid sendGrid() {
        SendGrid sendGrid = new SendGrid(apiKey);
        return sendGrid;
    }
}
