package de.rieckpil.learning.springboot2book.configuration;

import de.rieckpil.learning.springboot2book.CustomActuatorEndpoint;
import org.springframework.boot.actuate.autoconfigure.web.ManagementContextConfiguration;
import org.springframework.context.annotation.Bean;

@ManagementContextConfiguration
public class CustomActuatorEndpointConfig {

    @Bean
    public CustomActuatorEndpoint customActuatorEndpoint(){
        return new CustomActuatorEndpoint();
    }
}
