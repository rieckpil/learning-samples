package de.rieckpil.learning.springboot2bookreactive;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureWebTestClient
public class FilmControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void filmApiShouldWork() {

        FluxExchangeResult<Film> result = webTestClient
                .mutate()
                .build()
                .get().uri("/api/films")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Film.class);

        StepVerifier.create(result.getResponseBody())
                .consumeNextWith(film ->
                    assertThat(film.getTitle(), is("ACADEMY DINOSAUR")))
                .expectNextCount(9)
                .expectComplete()
                .verify();


    }

}