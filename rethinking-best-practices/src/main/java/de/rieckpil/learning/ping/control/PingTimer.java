package de.rieckpil.learning.ping.control;

import de.rieckpil.learning.ping.entity.Ping;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
@Startup
public class PingTimer {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    @Cache
    ConcurrentHashMap<String, Object> cache;

    @Inject
    Event<String> txListeners;

    @Inject
    PingMessageSender sender;

    @Schedule(second = "*/30", minute = "*", hour = "*", persistent = false)
    public void ping() {

        txListeners.fire("starting persisting of ping entity");

        try {
            sender.send();
        } catch (JMSException e) {
            e.printStackTrace();
        }

        System.out.println(Instant.now());

        Ping ping = new Ping(UUID.randomUUID().toString());
        cache.put(Instant.now().toString(), ping);

        entityManager.persist(ping);

        List<Ping> pingList = entityManager.createQuery("SELECT p FROM Ping p", Ping.class).getResultList();

        for (Object object : cache.values()) {
            System.out.println(object);
        }

        System.out.println(String.format("Size of pingList: '%s'", pingList.size()));

    }
}
