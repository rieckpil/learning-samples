package de.rieckpil.microprofile.customer.boundary;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.opentracing.Traced;

import de.rieckpil.microprofile.customer.entity.Customer;

import java.sql.Time;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Path("/customers")
@RequestScoped
@Counted
public class CustomerResource {

    @Inject
    private CustomerStore customerStore;

    @GET
    @Timed(name = "xyz")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCustomers() throws InterruptedException {

        Thread.sleep(5000);
        JsonArrayBuilder retVal = Json.createArrayBuilder();
        customerStore.getAllCustomers().stream().map(Customer::toJSON).forEach(retVal::add);
        return Response.ok(retVal.build()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewCustomer(JsonObject customerAsJson) {

        System.out.println(customerAsJson.toString());
        return Response.ok().build();
    }

    @GET
    @Traced
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpecificHelloWorld(@PathParam("id") String id) {

        return Response.ok(customerStore.getCustomerById(id).map(Customer::toJSON).get()).build();

    }

}
