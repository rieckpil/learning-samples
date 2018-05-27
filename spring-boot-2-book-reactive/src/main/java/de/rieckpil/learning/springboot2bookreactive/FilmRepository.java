package de.rieckpil.learning.springboot2bookreactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FilmRepository extends ReactiveMongoRepository<Film, String> {
}
