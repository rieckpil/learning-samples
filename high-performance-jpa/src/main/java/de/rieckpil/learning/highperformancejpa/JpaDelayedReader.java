package de.rieckpil.learning.highperformancejpa;

import de.rieckpil.learning.highperformancejpa.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@Slf4j
@Profile("secondlevel")
@Component
public class JpaDelayedReader implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void run(String... args) throws Exception {

        Thread.sleep(5 * 1000);

        Person personFromDb = entityManager.find(Person.class, 1L);

        String region = Person.class.getName();

        SecondLevelCacheStatistics statistics = entityManager.unwrap(Session.class).getSessionFactory().getStatistics
                ().getSecondLevelCacheStatistics(region);

        log.info("\nRegion: {}, \nStatistic: {}, \nEntries: {}", region, statistics, statistics.getEntries());

    }
}
