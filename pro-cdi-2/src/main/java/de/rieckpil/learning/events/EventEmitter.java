package de.rieckpil.learning.events;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class EventEmitter {

    @Inject
    Event<List<String>> newCustomers;

    @Inject
    Event<List<Integer>> newOrders;

    @Inject
    Event<String> information;

    public void emitEvents(@Observes @Initialized(ApplicationScoped.class) Object initialized) {
        newCustomers.fire(List.of("Duke", "Mike", "Andy"));
        newOrders.fire(List.of(1, 2, 3, 4, 5));
        information.fireAsync("Hello World")
                .handle((information, exception) -> {
                    if (exception != null) {
                        for (Throwable suppressed : exception.getSuppressed()) {
                            System.out.println(suppressed.getMessage());
                        }
                        return "default";
                    }
                    return information;
                })
                .thenAccept(information -> System.out.println(information + " was successfully processed async"));
    }
}
