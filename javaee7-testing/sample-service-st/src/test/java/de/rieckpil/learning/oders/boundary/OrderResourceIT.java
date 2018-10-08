package de.rieckpil.learning.oders.boundary;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Rule;
import org.junit.Test;

public class OrderResourceIT {

	@Rule
	public JAXRSClient provider = JAXRSClient.target("http://localhost:8080/sample-service/resources/orders");

	@Test
	public void testOrders() {
		Response response = this.provider.tut().request(MediaType.APPLICATION_JSON).get();
		assertThat(response.getStatus(), is(200));
		JsonObject result = response.readEntity(JsonObject.class);
		assertNotNull(result);
		System.out.println(result);
	}

	@Test
	public void testCrud() {

		JsonObject body = Json.createObjectBuilder().add("java", "rocks").build();

		Response response = provider.tut().request().post(Entity.json(body));

		assertThat(response.getStatus(), is(201));
		String uri = response.getHeaderString("Location");
		assertNotNull(uri);

		JsonObject result = JAXRSClient.target(uri).tut().request(MediaType.APPLICATION_JSON).get(JsonObject.class);
		assertThat(result.getString("java"), is("rocks"));

	}
}
