package de.rieckpil.learning.user.boundary;

import javax.ejb.Singleton;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Singleton
@ServerEndpoint(value = "/websocket")
public class WebsocketServer {

    private final List<Session> peers = Collections.synchronizedList(new ArrayList<>());

    @OnOpen
    public void onOpen(Session peer) {
        peers.add(peer);
    }

    @OnClose
    public void onClose(Session peer) {
        peers.remove(peer);
    }

    @OnError
    public void onError(Throwable t) {
        System.err.println(t.getMessage());
    }

    @OnMessage
    public void onMessage(String message, Session peer) {

        System.out.println("Incoming message to websocket server: " + message);
        System.out.println(peers.size());

        peers.stream().forEach(p -> System.out.println(p.getAsyncRemote()));

        peers.stream().filter((p) ->
                (p.isOpen())).forEach((p) -> {
            p.getAsyncRemote().sendText(message +
                    " - Total peers: " + peers.size());
        });
    }
}