package de.rieckpil.javaee8.control;

import de.rieckpil.javaee8.entity.Person;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class NameResolver {

    @Inject
    @ConfigProperty(name = "user_name")
    private String name;

    @PersistenceContext
    EntityManager entityManager;

    public String getName() {

        Person person = new Person();
        person.setName(name);

        entityManager.persist(person);

        return name.toUpperCase();
    }
}
