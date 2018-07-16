package de.rieckpil.learning.user.boundary;

import javax.websocket.*;
import javax.ws.rs.container.AsyncResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@ClientEndpoint
public class WebsocketClient {

    private final String asyncServer = "ws://localhost:9080/websocket";

    private Session session;
    private final AsyncResponse response;

    public WebsocketClient(AsyncResponse response) {
        this.response = response;
    }

    public void connect() {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        container.setAsyncSendTimeout(1000 * 2);
        try {
            container.connectToServer(this, new URI(asyncServer));
        } catch (URISyntaxException | DeploymentException |
                IOException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("message incoming to client: " + message);
        response.resume(message);
        this.close();
    }

    public void send(String message) {
        session.getAsyncRemote().sendText(message);
    }

    public void close() {
        try {
            session.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}