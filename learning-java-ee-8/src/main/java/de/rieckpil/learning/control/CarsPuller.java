package de.rieckpil.learning.control;

import de.rieckpil.learning.entity.Car;
import de.rieckpil.learning.entity.Color;
import de.rieckpil.learning.entity.EngineType;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

@Singleton
@Startup
public class CarsPuller {

    private Client client;
    private WebTarget webTarget;

    @PostConstruct
    public void init() {
        this.client = ClientBuilder.newBuilder().connectTimeout(1, TimeUnit.SECONDS).readTimeout(10,
                TimeUnit.SECONDS).build();
        this.webTarget = client.target("http://localhost:8080/learning-java-ee/resources/cars");
    }

    @Schedule(hour = "*", minute = "*", second = "*/30")
    public void getLatestCars() {

        System.out.println("Getting all cars");

        Response response = this.webTarget.request(MediaType.APPLICATION_JSON).get();

        System.out.println(response.getStatus());
        JsonArray jsonValues = response.readEntity(JsonArray.class);

        jsonValues.stream().forEach(value -> System.out.println(value.asJsonObject().toString()));

    }

    @Schedule(hour = "*", minute = "*", second = "*/30")
    public void postNewCar() {

        System.out.println("Posting new car");

        JsonObject entity = Json.createObjectBuilder()
                .add("color", Color.BLACK.name())
                .add("engineType", EngineType.ELECTRIC.name())
                .build();

        GenericType<List<Car>> carListType = new GenericType<List<Car>>() {
        };

        Response response = this.webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(entity));

        CompletionStage<Response> responseCompletionStage = webTarget.request(MediaType.APPLICATION_JSON).rx().get();
        System.out.println(responseCompletionStage.toCompletableFuture().join().getStatus());
    }

    @PreDestroy
    public void closeClient() {
        client.close();
    }
}
