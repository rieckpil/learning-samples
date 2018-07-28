package de.rieckpil.learning.ping.control;

import de.rieckpil.learning.ping.entity.Ping;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Singleton
@Startup
public class PingTimer {

    @PersistenceContext
    EntityManager entityManager;

    @Schedule(second = "*/30", minute = "*", hour = "*", persistent = false)
    public void ping() {
        System.out.println(Instant.now());

        entityManager.persist(new Ping(UUID.randomUUID().toString()));

        List<Ping> pingList = entityManager.createQuery("SELECT p FROM Ping p", Ping.class).getResultList();

        System.out.println(String.format("Size of pingList: '%s'", pingList.size()));

    }
}
