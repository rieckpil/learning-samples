package de.rieckpil.learning.boundary;

import de.rieckpil.learning.control.CarDecoder;
import de.rieckpil.learning.control.CarEncoder;
import de.rieckpil.learning.entity.Car;

import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/car", encoders = CarEncoder.class, decoders = CarDecoder.class)
public class CarWebsocket {

    @OnMessage
    public void onMessage(Car car, Session session) throws IOException, EncodeException {
        System.out.println("new car = " + car);
        session.getBasicRemote().sendObject(car);
    }


}


