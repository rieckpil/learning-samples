package de.rieckpil.learning.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import de.rieckpil.learning.Person;

@Repository
public class PersonJdbcRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Person> findAll() {
		return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper(Person.class));

	}

}
