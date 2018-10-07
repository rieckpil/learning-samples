package de.rieckpil.learning.order.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

public class OrderIT {

	private EntityManager em;
	private EntityTransaction tx;

	@Before
	public void init() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("it");
		this.em = emf.createEntityManager();
		this.tx = em.getTransaction();
	}
	
	@Test
	public void crud() {
		this.tx.begin();
		this.em.merge(new Order("42"));
		System.out.println("sucessfully stored the order 42");
		this.tx.commit();
	}
}
