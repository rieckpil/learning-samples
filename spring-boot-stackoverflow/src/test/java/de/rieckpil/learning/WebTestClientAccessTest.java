package de.rieckpil.learning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import reactor.core.publisher.Mono;

import java.net.http.HttpRequest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

// if you are using JUnit 4 this is also needed
// @RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class WebTestClientAccessTest {

  @Autowired
  private WebTestClient webTestClient;

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  public void notNull() {
    assertNotNull(webTestClient);
    assertNotNull(testRestTemplate);
  }

  @Test
  public void testAccess() {
    this.webTestClient.get()
      .uri("/public")
      .exchange()
      .expectStatus().is2xxSuccessful();

    this.webTestClient
      .mutate()
      .filter(removeContentTypeHeader())
      .build()
      .put()
      .uri("/public")
      .body(Mono.just("Test"), String.class)
      .exchange()
      .expectStatus().is4xxClientError();
  }

  private ExchangeFilterFunction removeContentTypeHeader() {
    return (clientRequest, next) -> {

      ClientRequest filteredRequest =
        ClientRequest.from(clientRequest).headers(httpHeaders -> httpHeaders.remove("Content-Type")
        ).build();

      filteredRequest.headers().forEach((k, v) -> System.out.println(k + ":" + v));

      return next.exchange(filteredRequest);
    };
  }

}
