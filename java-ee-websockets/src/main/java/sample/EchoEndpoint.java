package sample;

import java.io.IOException;
import java.nio.ByteBuffer;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.PongMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo")
public class EchoEndpoint {

    @OnMessage
    public void onMessage(Session session, String msg) {
        try {
            String prev = (String) session.getUserProperties()
                    .get("secretToken");
            System.out.println("incoming message: " + msg + " from " + session.getId() + " prev " + prev);
            for (Session sess : session.getOpenSessions()) {
                if (sess.isOpen()) {
                    sess.getBasicRemote().sendText(msg);
                }
            }
        } catch (IOException ex) {
        }
    }

    @OnMessage
    public void binaryMessage(Session session, ByteBuffer msg) {
        System.out.println("Binary message: " + msg.toString());
    }

    @OnMessage
    public void pongMessage(Session session, PongMessage msg) {
        System.out.println("Pong message: "
                + msg.getApplicationData().toString());
    }

    @OnOpen
    public void open(Session session, EndpointConfig conf) {
        session.getUserProperties().put("secretToken", "LOLOL");
        System.out.println("opened: " + session.getId());
    }

    @OnClose
    public void close(Session session, CloseReason reason) {
        System.out.println("closed:" + session.getId() + " " + reason.getReasonPhrase());
    }

}
