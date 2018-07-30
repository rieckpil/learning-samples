package de.rieckpil.learning.ping.control;

import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

public class PingTimerTest {

    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    @Before
    public void setUp() throws Exception {

        this.entityManager = Persistence.createEntityManagerFactory("test").createEntityManager();
        this.entityTransaction = this.entityManager.getTransaction();

    }

    @Test
    public void testTimer() {

        PingTimer cut = new PingTimer();

        cut.ping();

    }

}