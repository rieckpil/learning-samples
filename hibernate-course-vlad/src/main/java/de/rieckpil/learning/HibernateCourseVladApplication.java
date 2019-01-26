package de.rieckpil.learning;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariDataSource;

import de.rieckpil.learning.domain.Content;
import de.rieckpil.learning.domain.Post;
import de.rieckpil.learning.domain.PostStatus;
import de.rieckpil.learning.domain.PostStatusInfo;
import de.rieckpil.learning.domain.PublishInfo;
import de.rieckpil.learning.domain.Tag;

@SpringBootApplication
@EnableScheduling
public class HibernateCourseVladApplication implements CommandLineRunner {

	@Autowired
	private EntityManager em;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private HikariDataSource hikariDatasource;

	public static void main(String[] args) {
		SpringApplication.run(HibernateCourseVladApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws SQLException {

		System.out.println(dataSource.getConnection().getMetaData().getDefaultTransactionIsolation());
		System.out.println(dataSource.getConnection().getMetaData().getCatalogTerm());
		System.out.println(dataSource.getConnection().getMetaData().getCatalogSeparator());

		System.out.println(hikariDatasource.getJdbcUrl());

		System.out.println(DriverManager.getDrivers().nextElement());

		this.em.persist(new PostStatusInfo(0L, "PENDING", "Currenty queued"));
		this.em.persist(new PostStatusInfo(1L, "APPROVED", "Already approved"));
		this.em.persist(new PostStatusInfo(2L, "SPAM", "Marked as spam"));

		Content content1 = new Content("This is a cool post", "Hello World", Arrays.asList("Stuff", "Hello", "World"));
		Content content2 = new Content("Buy this cool stuff", "Spam 0815", Arrays.asList("Spam", "Ad", "Marketing"));

		Tag t1 = new Tag("JAVA");

		Post p1 = new Post("Hello World!", PostStatus.APPROVED, content1);
		p1.setPublishInfo(new PublishInfo());
		p1.addTag(t1);

		this.em.persist(p1);
		this.em.persist(new Post("Hello World!", PostStatus.APPROVED, content1));
		this.em.persist(new Post("Hello World!", PostStatus.SPAM, content2));
		this.em.persist(new Post("Hello World!", PostStatus.APPROVED, content1));
		this.em.persist(new Post("Hello World!", PostStatus.PENDING, content1));

		this.em.flush();

		this.em.unwrap(Session.class).doWork(con -> {
			try (Statement st = con.createStatement()) {
				ResultSet rs = st.executeQuery("SELECT * FROM post");
				while (rs.next()) {
					System.out.println(rs.getLong("id"));
				}
			}
		});

	}

	@Transactional
	@SuppressWarnings("unchecked")
	// @Scheduled(fixedDelay = 10000)
	public void findPost() throws IOException {
		Post p1 = this.em.find(Post.class, 1000L);
		System.out.println(p1.getStatusInfo().getDescription());

		List<String> resultList = this.em.createNativeQuery("SELECT jsonb_pretty(p.content) FROM post p")
				.getResultList();

		for (String string : resultList) {
			JsonNode node = mapper.readTree(string);
			System.out.println("[mainContent] == " + node.get("mainContent"));
		}
	}

}
