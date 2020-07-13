package de.rieckpl.learning.ws;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class GreetingControllerTest {

  @LocalServerPort
  private Integer port;

  private BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(1);

  @Test
  @WithMockUser(username = "duke")
  public void testWebSockets() throws InterruptedException, ExecutionException, TimeoutException {

    WebSocketClient webSocketClient = new StandardWebSocketClient();
    WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
    stompClient.setMessageConverter(new StringMessageConverter());

    StompSession session = stompClient
      .connect(getWsPath(), new StompSessionHandlerAdapter() {
      })
      .get(1, SECONDS);

    session.subscribe("/topic/greetings", new StompFrameHandler() {

      @Override
      public Type getPayloadType(StompHeaders headers) {
        return String.class;
      }

      @Override
      public void handleFrame(StompHeaders headers, Object payload) {
        blockingQueue.add(payload.toString());
        System.out.println("Response: " + payload);
      }
    });

    session.send("/app/hello", "Mike");

    Object payload = blockingQueue.poll(1, SECONDS);
    assertEquals("Hello, Mike!", payload);
  }

  private String getWsPath() {
    return String.format("ws://localhost:%d/ws-endpoint", port);
  }

}
