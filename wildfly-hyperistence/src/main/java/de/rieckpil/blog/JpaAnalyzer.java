package de.rieckpil.blog;

import io.hypersistence.optimizer.HypersistenceOptimizer;
import io.hypersistence.optimizer.core.config.JpaConfig;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Startup
@Singleton
public class JpaAnalyzer {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @PostConstruct
    public void init() {
        System.out.println("Analyzing JPA & Hibernate setup");
        new HypersistenceOptimizer(new JpaConfig(entityManagerFactory)).init();
    }
}
