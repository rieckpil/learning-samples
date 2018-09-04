package de.rieckpil.learning.control;

import de.rieckpil.learning.entity.Car;
import de.rieckpil.learning.entity.Color;
import de.rieckpil.learning.entity.EngineType;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.StringReader;

public class CarDecoder implements Decoder.Text<Car> {

    @Override
    public Car decode(String s) throws DecodeException {
        JsonObject jsonObject = Json.createReader(new StringReader((s))).readObject();
        Car car = new Car();
        car.setEngineType(EngineType.valueOf(jsonObject.getString("engineType")));
        car.setColor(Color.valueOf(jsonObject.getString("color")));
        return car;
    }

    @Override
    public boolean willDecode(String s) {
        return false;
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}
