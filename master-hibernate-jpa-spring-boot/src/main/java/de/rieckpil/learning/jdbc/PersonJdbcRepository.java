package de.rieckpil.learning.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import de.rieckpil.learning.Person;

@Repository
public class PersonJdbcRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	class PersonRowMapper implements RowMapper<Person> {
		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person result = new Person();
			result.setId(rs.getInt("id"));
			result.setName(rs.getString("name"));
			result.setLocation(rs.getString("location"));
			result.setBirthDate(rs.getTimestamp("birth_date").toLocalDateTime());
			return result;
		}
	}

	public List<Person> findAll() {
		return jdbcTemplate.query("SELECT * FROM person", new PersonRowMapper());
	}

	public Person findById(int id) {
		return jdbcTemplate.queryForObject("SELECT * FROM person where id=?", new Object[] { id },
				new BeanPropertyRowMapper<Person>(Person.class));
	}

	public int deleteById(int id) {
		return jdbcTemplate.update("DELETE FROM person WHERE id=?", new Object[] { id });
	}

	public int insert(Person person) {
		return jdbcTemplate.update("INSERT INTO person (id, name, location, birth_date) VALUES (?, ?, ?, ?)",
				new Object[] { person.getId(), person.getName(), person.getLocation(),
						Timestamp.valueOf(person.getBirthDate()) });
	}

}
