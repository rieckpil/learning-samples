package de.rieckpil.learning;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class HelloWorld {

    @PostConstruct
    public void sayHello() {
        System.out.println("Hello World CDI");
    }
}
