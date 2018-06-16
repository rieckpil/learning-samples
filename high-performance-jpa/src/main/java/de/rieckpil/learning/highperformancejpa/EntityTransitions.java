package de.rieckpil.learning.highperformancejpa;

import de.rieckpil.learning.highperformancejpa.entity.Person;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Component
public class EntityTransitions implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Person p = new Person();
        p.setName("Rieckpil");

        entityManager.persist(p);
        TypedQuery<Person> select = entityManager.createNamedQuery("findAllPersons", Person.class);
        Person singleResult = select.getSingleResult();
        System.out.println("ID of the first person is: " + singleResult.getId());

    }
}
