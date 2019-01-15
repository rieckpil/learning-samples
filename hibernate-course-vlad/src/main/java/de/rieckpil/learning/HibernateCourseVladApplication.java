package de.rieckpil.learning;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.rieckpil.learning.domain.Post;

@SpringBootApplication
public class HibernateCourseVladApplication implements CommandLineRunner {

	@Autowired
	private EntityManager em;

	public static void main(String[] args) {
		SpringApplication.run(HibernateCourseVladApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		this.em.persist(new Post("Hello World!"));
	}

}
