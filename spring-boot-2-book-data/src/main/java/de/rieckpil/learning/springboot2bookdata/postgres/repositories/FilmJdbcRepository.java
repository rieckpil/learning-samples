package de.rieckpil.learning.springboot2bookdata.postgres.repositories;

import de.rieckpil.learning.springboot2bookdata.postgres.entities.Film;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.List;

@Repository
public class FilmJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public FilmJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Film> findAll() {

        return this.jdbcTemplate.query(
                "SELECT title, release_year from film",
                (rs, rowNum) -> new Film(
                        rs.getString("title"),
                        Year.of(rs.getInt("release_year"))
                )
        );

    }
}
