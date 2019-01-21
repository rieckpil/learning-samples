package de.rieckpil.learning;

import java.sql.DriverManager;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.zaxxer.hikari.HikariDataSource;

import de.rieckpil.learning.domain.Post;
import de.rieckpil.learning.domain.PostStatus;
import de.rieckpil.learning.domain.PostStatusInfo;

@SpringBootApplication
@EnableScheduling
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

		this.em.persist(new PostStatusInfo(0L, "PENDING", "Currenty queued"));
		this.em.persist(new PostStatusInfo(1L, "APPROVED", "Already approved"));
		this.em.persist(new PostStatusInfo(2L, "SPAM", "Marked as spam"));

		this.em.persist(new Post("Hello World!", PostStatus.APPROVED));
		this.em.persist(new Post("Hello World!", PostStatus.APPROVED));
		this.em.persist(new Post("Hello World!", PostStatus.SPAM));
		this.em.persist(new Post("Hello World!", PostStatus.APPROVED));
		this.em.persist(new Post("Hello World!", PostStatus.PENDING));

	}

	@Transactional
	@Scheduled(fixedDelay = 1000)
	public void findPost() {
		Post p1 = this.em.find(Post.class, 1L);
		System.out.println(p1.getStatusInfo().getDescription());
	}

}
