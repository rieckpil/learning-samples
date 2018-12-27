package sample;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint
public class ETFClient {

    @OnOpen
    public void onOpen(Session userSession) {
        System.out.println("--- opening websocket CLIENT");
    }

    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        System.out.println("--- closing websocket CLIENT");
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("--- client incoming message: " + message);
    }

}
