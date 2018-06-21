package de.rieckpil.learning.highperformancejpa;

import de.rieckpil.learning.highperformancejpa.entity.Address;
import de.rieckpil.learning.highperformancejpa.entity.Person;
import de.rieckpil.learning.highperformancejpa.projections.PersonSummary;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class JpaFetchingFiller implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void run(String... args) {

        Person p1 = new Person();
        p1.setName("Philip");

        Address address1 = new Address();
        address1.setStreet("Main Street");
        p1.setAddress(address1);

        Person p2 = new Person();
        p2.setName("Tom");

        Address address2 = new Address();
        address2.setStreet("Meier Street");
        p2.setAddress(address2);

        entityManager.persist(p1);
        entityManager.persist(p2);

        entityManager.flush();

        List<PersonSummary> personSummaryList = entityManager.createQuery(
                "select new de.rieckpil.learning.highperformancejpa.projections.PersonSummary(p.name, a.street) " +
                        " FROM Person p JOIN p.address a"
        ).getResultList();

        for (PersonSummary personSummary : personSummaryList) {
            System.out.println("personSummary = " + personSummary);
        }

    }
}
