package de.rieckpil.learning.highperformancejpa;

import de.rieckpil.learning.highperformancejpa.entity.Person;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional
public class JpaExplicitLocking implements CommandLineRunner {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void run(String... args) {
        
        Person p = entityManager.find(Person.class, 31L, LockModeType.PESSIMISTIC_READ);

        System.out.println("p = " + p);

        Person p2 = entityManager.find(Person.class, 32L);
        System.out.println("p2 = " + p2);
        entityManager.lock(p2, LockModeType.PESSIMISTIC_READ);


    }
}
