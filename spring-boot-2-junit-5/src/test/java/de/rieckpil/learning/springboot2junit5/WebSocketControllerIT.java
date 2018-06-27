package de.rieckpil.learning.springboot2junit5;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebSocketControllerIT {

    @LocalServerPort
    private int port;

    private static final String USERNAME = "RIECKPIL";
    private StompSession stompSession;
    private ObjectMapper objectMapper;

    private CompletableFuture<List<String>> completableFutureForListOfStrings;
    private CompletableFuture<List<Person>> completableFutureForListOfPersons;

    @BeforeEach
    public void beforeEach() throws Exception {
        this.completableFutureForListOfStrings = new CompletableFuture<>();
        this.completableFutureForListOfPersons = new CompletableFuture<>();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.findAndRegisterModules();

        List<Transport> transports = new ArrayList<>(1);
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));

        WebSocketStompClient stompClient = new WebSocketStompClient(new SockJsClient(transports));
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        this.stompSession = stompClient.connect("ws://localhost:" + port + "/socket", new StompSessionHandlerAdapter() {
        }).get(5, TimeUnit.SECONDS);
    }

    @Test
    public void testSayHello() throws Exception {

        stompSession.subscribe("/topic/hello", new ListOfStringStompFrameHandler());

        List<String> result = completableFutureForListOfStrings.get(10, TimeUnit.SECONDS);

        Assertions.assertEquals(3, result.size());
        Assertions.assertTrue(result.contains("Hello"));
        Assertions.assertTrue(result.contains("World"));
        Assertions.assertTrue(result.contains("!"));
    }

    @Test
    public void testSayHelloWithUsername() throws Exception {

        stompSession.subscribe("/topic/hello/" + USERNAME, new ListOfStringStompFrameHandler());

        List<String> result = completableFutureForListOfStrings.get(10, TimeUnit.SECONDS);

        Assertions.assertEquals(4, result.size());
        Assertions.assertTrue(result.contains("Hello"));
        Assertions.assertTrue(result.contains("World"));
        Assertions.assertTrue(result.contains(USERNAME));
        Assertions.assertTrue(result.contains("!"));
        Assertions.assertTrue(result.containsAll(Arrays.asList("Hello", USERNAME, "!", "World")));
    }

    @Test
    @Sql("/INSERT_THREE_PERSONS.sql")
    public void testGetAllPersons() throws Exception {

        stompSession.subscribe("/topic/persons", new ListOfPersonStompFrameHandler());

        List<Person> result = completableFutureForListOfPersons.get(10, TimeUnit.SECONDS);

        TypeReference<List<Person>> typeRef
                = new TypeReference<List<Person>>() {
        };

        System.out.println(objectMapper.writeValueAsString(result));

        List<Person> serializedResult = objectMapper.readValue(objectMapper.writeValueAsString(result), typeRef);

        Assertions.assertEquals(5, serializedResult.size());
        Assertions.assertTrue(serializedResult.stream().map(p -> p.getFirstName()).collect(Collectors.toList()).containsAll(Arrays.asList("John", "Peter", "Bat", "Mike", "Philip")));

    }

    private class ListOfStringStompFrameHandler implements StompFrameHandler {

        @Override
        public Type getPayloadType(StompHeaders stompHeaders) {
            return List.class;
        }

        @SuppressWarnings("unchecked")
        @Override
        public void handleFrame(StompHeaders stompHeaders, Object data) {
            completableFutureForListOfStrings.complete((List<String>) data);
        }
    }

    private class ListOfPersonStompFrameHandler implements StompFrameHandler {

        @Override
        public Type getPayloadType(StompHeaders stompHeaders) {
            return List.class;
        }

        @SuppressWarnings("unchecked")
        @Override
        public void handleFrame(StompHeaders stompHeaders, Object data) {
            completableFutureForListOfPersons.complete((List<Person>) data);
        }
    }
}