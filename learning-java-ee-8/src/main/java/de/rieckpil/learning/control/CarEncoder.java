package de.rieckpil.learning.control;

import de.rieckpil.learning.entity.Car;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class CarEncoder implements Encoder.Text<Car> {
    @Override
    public String encode(Car object) throws EncodeException {
        return Json.createObjectBuilder()
                .add("color", object.getColor().name())
                .add("engineType", object.getEngineType().name())
                .build().toString();
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}
