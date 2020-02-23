package de.rieckpil.learning

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.client.WebClient

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SpringBootTestingWeblayerApplicationTests {

  @LocalServerPort
  private lateinit var port: Integer

  @Autowired
  private lateinit var webTestClient: WebTestClient

  @Test
  fun contextLoads() {

    println(WebClient.builder()
      .baseUrl("http://localhost:$port")
      .build()
      .get()
      .uri("/api")
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .toEntity(String::class.java)
      .block())

    println(webTestClient.get().uri("/api").exchange().returnResult(String.javaClass))
  }

  @Test
  fun testLogging() {
  }
}
