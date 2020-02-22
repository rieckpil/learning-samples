package de.rieckpil.learning

import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MainControllerIT {

  @Autowired
  private lateinit var webTestClient: WebTestClient

  @Test
  fun testAccess() {
    this.webTestClient
      .get()
      .uri("/data")
      .exchange()
      .expectStatus().is2xxSuccessful
      .expectHeader().contentType(MediaType.APPLICATION_JSON)
      .expectBody()
      .jsonPath("$.full.name", `is`("Duke"))
  }

}
