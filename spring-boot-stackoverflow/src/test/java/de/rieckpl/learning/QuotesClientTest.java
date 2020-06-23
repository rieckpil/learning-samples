package de.rieckpl.learning;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class QuotesClientTest {

  private QuotesClient quotesClient;
  private MockWebServer server;

  @BeforeEach
  public void setup() {
    this.server = new MockWebServer();
    this.quotesClient = new QuotesClient(WebClient.builder(), server.url("/").toString());
  }

  @Test
  public void test() {

    server.enqueue(new MockResponse()
      .setStatus("HTTP/1.1 200")
      .setBody("{\"bar\":\"barbar\",\"foo\":\"foofoo\"}")
      .addHeader("Content-Type", "application/json"));

    JsonNode data = quotesClient.getData();
    assertNotNull(data);

    System.out.println(data);

  }

}
