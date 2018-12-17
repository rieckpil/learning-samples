package de.rieckpil.learning;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class SpringBootOracleExampleApplication implements CommandLineRunner {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootOracleExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM test");

		for (Map<String, Object> row : rows) {
			System.out.println(row.get("id"));
			System.out.println(row.get("name"));

		}

	}

}
