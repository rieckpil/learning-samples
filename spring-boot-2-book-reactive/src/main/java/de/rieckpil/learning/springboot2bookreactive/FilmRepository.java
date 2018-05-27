package de.rieckpil.learning.springboot2bookreactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

public interface FilmRepository extends ReactiveMongoRepository<Film, String> {

    Flux<Film> streamAllByTitleNotNull();

}
