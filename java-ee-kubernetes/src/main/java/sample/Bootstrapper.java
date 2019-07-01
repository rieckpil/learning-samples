package sample;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class Bootstrapper {

    @Inject
    @ConfigProperty(name = "NAME")
    private String name;

    @PostConstruct
    public void printVariables() {
        System.out.println("--- name: " + name);
        System.out.println("--- Hello World!");
    }
}
