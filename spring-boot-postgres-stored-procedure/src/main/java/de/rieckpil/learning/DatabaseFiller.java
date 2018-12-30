package de.rieckpil.learning;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class DatabaseFiller {

	@Autowired
	private UserRepository repository;

	@PersistenceContext
	private EntityManager em;

	@EventListener(ApplicationReadyEvent.class)
	public void init() {

		for (int i = 0; i < 100; i++) {
			repository.save(new User("Duke", UUID.randomUUID().toString()));
		}

		System.out.println(em.createNamedStoredProcedureQuery("inc").setParameter("inParam1", 1).getSingleResult());

	}

}
