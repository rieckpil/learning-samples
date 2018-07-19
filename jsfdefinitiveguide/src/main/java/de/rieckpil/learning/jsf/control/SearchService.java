package de.rieckpil.learning.jsf.control;

import de.rieckpil.learning.jsf.entity.Result;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Singleton
public class SearchService {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void init() {

        Result r1 = new Result();
        r1.setDescription("LOL");
        r1.setName("1");

        Result r2 = new Result();
        r2.setDescription("TROLO");
        r2.setName("2");

        em.persist(r1);
        em.persist(r2);
    }

    public List<Result> getResults(String query) {

        if (query == null || query.isEmpty()) {
            query = "SELECT r FROM Result r";
        }

        return em.createQuery(query, Result.class).getResultList();

    }
}
