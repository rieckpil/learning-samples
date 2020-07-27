package de.rieckpil.learning.client;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class UserClient {

  private final RestTemplate restTemplate;

  public UserClient(RestTemplateBuilder restTemplate) {
    this.restTemplate = restTemplate.build();
  }

  public JsonNode getData() {
    return null;
  }
}
