package de.rieckpil.learning;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class PostgresDbIT {

@Container
 static final MariaDBContainer DATABASE =
  new MariaDBContainer<>(DockerImageName.parse("mariadb:10.3.6"))
    .withDatabaseName("maindb")
    .withUsername("duke")
    .withPassword("duke")
    .withInitScript("CREATE_SECOND_DB.sql");


  @DynamicPropertySource
  static void properties(DynamicPropertyRegistry registry) {
    System.out.println(DATABASE.getJdbcUrl());
  }

  @Test
  void testMe() throws IOException, InterruptedException {
    System.out.println("works!");
  }

}
