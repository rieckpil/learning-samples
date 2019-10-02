package de.rieckpil.learning;

import javax.enterprise.context.*;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class MyLifecycleListener {

    public void onInitialization(@Observes @Initialized(ApplicationScoped.class) Object o) {
        System.out.println("Application scope initialized");
    }

    public void onBeforeDestruction(@Observes @BeforeDestroyed(ApplicationScoped.class) Object o) {
        System.out.println("Application scope about to be destroyed");
    }

    public void onDestroyed(@Observes
                            @Destroyed(RequestScoped.class) Object o) {
        System.out.println("Application scope destroyed");
    }
}
