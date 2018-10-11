package de.rieckpil.learning.echo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class EchoEndpointIT {

    private Session session;
    private MessageHandler messageHandler;

    @Before
    public void init() throws URISyntaxException, DeploymentException, IOException {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        URI uri = new URI("ws://localhost:8080/sample-service/echo");
        this.session = container.connectToServer(new Endpoint() {
            @Override
            public void onOpen(Session sn, EndpointConfig ec) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }, uri);
        
        this.messageHandler = new MessageHandler();
        this.session.addMessageHandler(String.class, this.messageHandler);
    }
    
    @Test
    public void testEcho() throws InterruptedException {
        String expectedMessage = "DUKE";
        this.session.getAsyncRemote().sendText(expectedMessage);
        String actual = this.messageHandler.getMessage();
        assertEquals("+" + expectedMessage, actual);
    }

}
