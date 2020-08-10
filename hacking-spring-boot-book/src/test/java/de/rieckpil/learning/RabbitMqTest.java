package de.rieckpil.learning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
@AutoConfigureWebTestClient
public class RabbitMqTest {

  @Container
  static RabbitMQContainer container = new RabbitMQContainer();

  @Autowired
  private WebTestClient webTestClient;

  @Autowired
  private ItemRepository itemRepository;

  @DynamicPropertySource
  static void configure(DynamicPropertyRegistry registry) {
    registry.add("spring.rabbitmq.host", container::getContainerIpAddress);
    registry.add("spring.rabbitmq.port", container::getAmqpPort);
  }

  @Test
  void test() throws InterruptedException {
    this.webTestClient.post().uri("/amqp/items")
      .bodyValue(new Item("Duke", "Bar", 19.99))
      .exchange()
      .expectStatus().isCreated()
      .expectBody();

    Thread.sleep(1500L);

    this.webTestClient.post().uri("/amqp/items")
      .bodyValue(new Item("Duke42", "Bar", 42.00))
      .exchange()
      .expectStatus().isCreated()
      .expectBody();

    Thread.sleep(1500L);

    this.itemRepository
      .findAll()
      .as(StepVerifier::create)
      .expectNextMatches(item -> {
        assertThat(item.getName()).isEqualTo("Duke");
        return true;
      }).expectNextMatches(item -> {
      assertThat(item.getName()).isEqualTo("Duke42");
      return true;
    });
  }
}
