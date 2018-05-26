package de.rieckpil.learning.springboot2bookdata.postgres.repositories;

import de.rieckpil.learning.springboot2bookdata.jooq.tables.Film;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.List;

import static de.rieckpil.learning.springboot2bookdata.jooq.tables.Film.FILM;

@Repository
public class FilmJOOQRepository {

    private final DSLContext ctx;

    public FilmJOOQRepository(DSLContext ctx) {
        this.ctx = ctx;
    }

    public List<Film> findAll() {

        return this.ctx
                .select(FILM.TITLE, FILM.RELEASE_YEAR)
                .from(FILM)
                .fetch(r -> new Film(
                        r.get(FILM.TITLE),
                        Year.of(r.get(FILM.RELEASE_YEAR)))
                );

    }
}
