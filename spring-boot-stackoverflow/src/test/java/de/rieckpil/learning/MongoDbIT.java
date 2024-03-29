package de.rieckpil.learning;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class MongoDbIT {

  @Container
  public static MongoDBContainer mongoDBContainer = new MongoDBContainer();

  @DynamicPropertySource
  static void mongoDbProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
  }

  @Test
  void testMe() throws IOException, InterruptedException {
    var result = mongoDBContainer.execInContainer("ls", "-al", "|", "grep", "*.txt");
    System.out.println("RESULT: " + result.getStdout());
  }

}
