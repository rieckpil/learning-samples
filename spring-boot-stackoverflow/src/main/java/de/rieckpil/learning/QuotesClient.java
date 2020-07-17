package de.rieckpil.learning;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class QuotesClient {

  private final WebClient webClient;

  public QuotesClient(WebClient.Builder builder, @Value("${client.baseUrl}") String baseUrl) {
    this.webClient = builder.baseUrl(baseUrl).build();
  }

  public JsonNode getData() {
    return this.webClient
      .get()
      .retrieve()
      .bodyToMono(JsonNode.class)
      .block();
  }
}
