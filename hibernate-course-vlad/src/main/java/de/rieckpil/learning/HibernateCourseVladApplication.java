package de.rieckpil.learning;

import java.sql.DriverManager;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zaxxer.hikari.HikariDataSource;

import de.rieckpil.learning.domain.Post;

@SpringBootApplication
public class HibernateCourseVladApplication implements CommandLineRunner {

	@Autowired
	private EntityManager em;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private HikariDataSource hikariDatasource;

	public static void main(String[] args) {
		SpringApplication.run(HibernateCourseVladApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		System.out.println(dataSource.getConnection().getMetaData().getDefaultTransactionIsolation());
		System.out.println(dataSource.getConnection().getMetaData().getCatalogTerm());
		System.out.println(dataSource.getConnection().getMetaData().getCatalogSeparator());

		System.out.println(hikariDatasource.getJdbcUrl());

		System.out.println(DriverManager.getDrivers().nextElement());

		this.em.persist(new Post("Hello World!"));
		this.em.persist(new Post("Hello World!"));
		this.em.persist(new Post("Hello World!"));
		this.em.persist(new Post("Hello World!"));
		this.em.persist(new Post("Hello World!"));
	}

}
