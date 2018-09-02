package de.rieckpil.learning.control;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@ApplicationScoped
public class ConfigurationExposer {

    private Properties properties;

    @PostConstruct
    public void init() {
        try (InputStream resourceAsStream = ConfigurationExposer.class.getResourceAsStream("/application.properties")) {
                properties = new Properties();
                properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Produces
    @Config("unused")
    public String exposeConfig(InjectionPoint injectionPoint) {
        String key = injectionPoint.getAnnotated().getAnnotation(Config.class).value();
        return properties.getProperty(key);

    }

}
