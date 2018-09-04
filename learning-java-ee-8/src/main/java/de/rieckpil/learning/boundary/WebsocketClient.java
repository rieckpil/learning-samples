package de.rieckpil.learning.boundary;

import de.rieckpil.learning.control.CarDecoder;
import de.rieckpil.learning.control.CarEncoder;
import de.rieckpil.learning.entity.Car;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.Session;

@ClientEndpoint(encoders = CarEncoder.class, decoders = CarDecoder.class)
public class WebsocketClient {

    @OnMessage
    public void onMessage(Car car, Session session) {
        System.out.println("client got new message");
    }


}
