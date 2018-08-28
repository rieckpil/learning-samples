package de.rieckpil.learning.boundary;

import de.rieckpil.learning.entity.Car;
import de.rieckpil.learning.entity.Specification;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.stream.JsonCollectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("cars")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarsResource {

    @Inject
    CarManufacturer carManufacturer;

    @Context
    UriInfo uriInfo;

    @GET
    public JsonArray retrieveCars() {
        List<Car> cars = carManufacturer.retrieveCars();

        JsonArray result = cars.stream()
                .map(c -> Json.createObjectBuilder()
                        .add("color", c.getColor().name())
                        .add("engine", c.getEngineType().name())
                        .add("identifier", c.getIdentifier())
                        .build())
                .collect(JsonCollectors.toJsonArray());

        return result;
    }

    @GET
    @Path("{id}")
    public Car retrieveCar(@PathParam("id") String id) {
        return carManufacturer.retrieveCar(id);
    }

    @POST
    public Response createCar(@Valid @NotNull Specification specification) {

        // Color color = Color.valueOf(jsonObject.getString("color"));
        // EngineType engineType = EngineType.valueOf(jsonObject.getString("engineType"));

        Car car = carManufacturer.manufactureCar(specification);

        URI uri = uriInfo.getBaseUriBuilder()
                .path(CarsResource.class)
                .path(CarsResource.class, "retrieveCar")
                .build(car.getIdentifier());

        return Response.status(Response.Status.CREATED)
                .header(HttpHeaders.LOCATION, uri)
                .build();
    }

}
