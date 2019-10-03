package de.rieckpil.learning.events;

import javax.annotation.Priority;
import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;
import javax.enterprise.event.Reception;
import javax.enterprise.event.TransactionPhase;
import javax.enterprise.inject.spi.EventMetadata;
import java.util.List;

public class EventListener {
    public void listenToEvent(@Observes(notifyObserver = Reception.ALWAYS, during = TransactionPhase.IN_PROGRESS) @Priority(100) List<String> newCustomers,
                              EventMetadata eventMetadata) {
        System.out.println(eventMetadata.getInjectionPoint().getBean().getBeanClass());
        newCustomers.forEach(customer -> System.out.println("New customer arrived: " + customer.toLowerCase()));
    }

    public void listenToEventAsyncUpper(@ObservesAsync String information) {
        System.out.println("EventListener.listenToEventAsyncUpper: " + information.toUpperCase());
        throw new RuntimeException("ERROR");
    }

    public void listenToEventAsyncLower(@ObservesAsync String information) {
        System.out.println("EventListener.listenToEventAsyncLower: " + information.toLowerCase());
        throw new IllegalArgumentException("ERROR 2");
    }
}
