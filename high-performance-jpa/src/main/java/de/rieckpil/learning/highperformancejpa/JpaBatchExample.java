package de.rieckpil.learning.highperformancejpa;

import de.rieckpil.learning.highperformancejpa.entity.Person;
import org.hibernate.Session;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Profile("batch")
public class JpaBatchExample implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) {

        entityManager.unwrap(Session.class).setJdbcBatchSize(5);

        for (int i = 0; i < 10; i++) {
            entityManager.persist(new Person(Long.valueOf(i), String.format("Name: %d", i + 1)));
        }

        entityManager.flush();

        List<Person> persons = entityManager.createQuery("select p from Person p", Person.class).getResultList();

        persons.forEach(person -> person.setName(person.getName().toUpperCase()));

    }
}
