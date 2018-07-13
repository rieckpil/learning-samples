package de.rieckpil.learning.recipes.jaxrs;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.SseEventSource;

import static de.rieckpil.learning.recipes.jaxrs.MockServer.BASE_PATH;

public class ClientConsumer {

    public static final Client CLIENT = ClientBuilder.newClient();
    public static final WebTarget WEB_TARGET =
            CLIENT.target(MockServer.CONTEXT
                    + BASE_PATH);

    public static void main(String[] args) {
        consume();
    }

    private static void consume() {

        try (final SseEventSource sseSource =
                     SseEventSource
                             .target(WEB_TARGET)
                             .build()) {

            sseSource.register(System.out::println);
            sseSource.open();

            for (int counter = 0; counter < 5; counter++) {
                System.out.println(" ");
                for (int innerCounter = 0; innerCounter < 5;
                     innerCounter++) {
                    WEB_TARGET.request().post(Entity.json("event "
                            + innerCounter));
                }
                Thread.sleep(1000);
            }

            CLIENT.close();
            System.out.println("\n All messages consumed");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
