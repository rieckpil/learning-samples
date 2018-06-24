package de.rieckpil.learning.highperformancejpa;

import de.rieckpil.learning.highperformancejpa.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.cache.internal.StandardQueryCache;
import org.hibernate.jpa.QueryHints;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Component
@Profile("querycaching")
@Transactional
public class HibernateQueryCaching implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void run(String... args) {

        List<Person> persons = entityManager.createQuery("SELECT p FROM Person p ORDER BY p.id", Person.class)
                .setMaxResults(5)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .getResultList();

        for (Person p : persons) {
            System.out.println("p = " + p);
            p.setName(p.getName().toUpperCase());
        }

    }
}
