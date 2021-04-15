package de.rieckpil.learning.testcontainers;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.CreateSecretRequest;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SECRETSMANAGER;

@Testcontainers
public class LocalStackSecretsManagerTest {

  DockerImageName localstackImage = DockerImageName.parse("localstack/localstack:0.11.3");

  @Container
  public LocalStackContainer localstack = new LocalStackContainer(localstackImage)
    .withServices(SECRETSMANAGER)
    .withEnv("LOCALSTACK_HOSTNAME", "localhost")
    .withEnv("HOSTNAME", "localhost");

  @Test
   void someTestMethod() {
    AWSSecretsManager secretsManager = AWSSecretsManagerClientBuilder.standard()
      .withCredentials(localstack.getDefaultCredentialsProvider())
      .withEndpointConfiguration(localstack.getEndpointConfiguration(SECRETSMANAGER))
      .build();

    String secretString = "usrnme";

    CreateSecretRequest request = new CreateSecretRequest()
      .withName("test")
      .withRequestCredentialsProvider(localstack.getDefaultCredentialsProvider());

    secretsManager.createSecret(request);
  }
}
