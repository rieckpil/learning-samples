package de.rieckpil.learning.highperformancejpa;

import de.rieckpil.learning.highperformancejpa.entity.Person;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.sql.SQLException;

@Component
public class EntityTransitions implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = SQLException.class, dontRollbackOn =
            NullPointerException.class)
    public void run(String... args) {

        Person p = new Person();
        p.setName("Rieckpil");

        entityManager.persist(p);
        TypedQuery<Person> select = entityManager.createNamedQuery("findAllPersons", Person.class);
        Person singleResult = select.getSingleResult();
        System.out.println("ID of the first person is: " + singleResult.getId());

    }
}
