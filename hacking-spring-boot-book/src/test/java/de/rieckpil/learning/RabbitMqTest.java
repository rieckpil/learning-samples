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
  void test() {
    System.out.println("Context loads ...");
  }
}
