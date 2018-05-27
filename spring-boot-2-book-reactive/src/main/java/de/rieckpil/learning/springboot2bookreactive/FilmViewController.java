package de.rieckpil.learning.springboot2bookreactive;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.TEXT_HTML;

@Controller
@RequiredArgsConstructor
public class FilmViewController {

    private final FilmRepository filmRepository;

    @GetMapping("/index")
    public String index(final Model model) {
        Flux<String> filmsBeingWatched =
                filmRepository.streamAllByTitleNotNull()
                        .map(f -> f.getId() + ": " + f.getTitle())
                        .distinct();

        model.addAttribute("filmsBeingWatched",
                new ReactiveDataDriverContextVariable(
                        filmsBeingWatched, 1));
        model.addAttribute("info", "Hello World!");

        return "index";
    }
}
