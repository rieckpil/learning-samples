package de.rieckpil.learning;

import de.rieckpil.learning.customscope.CustomScoped;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Named;

@Named
@MyQualifier
@CustomScoped
public class MyService {

    public void sayHello() {
        System.out.println("Hello World from managed bean");
    }

    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        // CDI.current().select(Object.class).stream().forEach(System.out::println);
    }
}
