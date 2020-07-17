package de.rieckpil.learning;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.S3;

@Testcontainers
@SpringBootTest
public class LocalStackIT {

  @Container
  public static LocalStackContainer localStackContainer = new LocalStackContainer("0.11.2")
    .withServices(S3)
    .withCopyFileToContainer(MountableFile.forClasspathResource("init.sh", 744),
      "/docker-entrypoint-initaws.d/init.sh");

  @Test
  public void test() {
    System.out.println("inside");
  }
}
