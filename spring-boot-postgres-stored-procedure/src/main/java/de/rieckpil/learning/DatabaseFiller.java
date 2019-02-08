package de.rieckpil.learning;

import java.sql.SQLException;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class DatabaseFiller {

	@Autowired
	private UserRepository repository;

	@Autowired
	private DataSource dataSource;

	@PersistenceContext
	private EntityManager em;

	@EventListener(ApplicationReadyEvent.class)
	public void init() throws SQLException {

		System.out.println("Auto commit: " + dataSource.getConnection().getAutoCommit());
		System.out.println("JDBC Major version: " + dataSource.getConnection().getMetaData().getJDBCMajorVersion());
		System.out.println("JDBC Minor version: " + dataSource.getConnection().getMetaData().getJDBCMinorVersion());

		for (int i = 0; i < 100; i++) {
			repository.save(new User("Duke" + i, UUID.randomUUID().toString()));
		}

		System.out.println(em.createNamedStoredProcedureQuery("inc").setParameter("inParam1", 1).getSingleResult());
		System.out.println(em.createNamedStoredProcedureQuery("inc").setParameter("inParam1", -1).getSingleResult());
		System.out.println(em.createNamedStoredProcedureQuery("inc").setParameter("inParam1", 10).getSingleResult());

	}

}
