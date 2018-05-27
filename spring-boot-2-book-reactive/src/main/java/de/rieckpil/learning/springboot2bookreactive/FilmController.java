package de.rieckpil.learning.springboot2bookreactive;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
public class FilmController {

    private final FilmRepository filmRepository;

    @GetMapping(path= "/api/films",  produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Film> getAll() {
        return filmRepository.findAll(Sort.by("title").ascending());
    }

    @GetMapping("/helloworld")
    public Mono<String> getGreeting(@RequestParam(defaultValue = "World") String name) {
        return Mono.just("Hello")
                .flatMap(s -> Mono.just(s + ", " + name + "!\n"));
    }

    @GetMapping(
            path = "/streaming",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE
    )
    public Flux<String> streamGreetings() {
        return Flux.interval(Duration.ofSeconds(1))
                .log()
                .map(i -> String.format("Hello (%d)", i));
    }
}
