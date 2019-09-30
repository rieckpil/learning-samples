package de.rieckpil.learning;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.spi.CDI;

@Startup
@Singleton
public class HelloWorld {

    @PostConstruct
    public void sayHello() {

        CDI.current().select(MyService.class).get().sayHello();

    }
}
