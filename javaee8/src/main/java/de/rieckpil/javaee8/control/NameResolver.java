package de.rieckpil.javaee8.control;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class NameResolver {

    @Inject
    @ConfigProperty(name = "user_name")
    private String name;

    public String getName() {
        return name.toUpperCase();
    }
}
