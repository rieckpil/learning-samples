package de.rieckpil.learning.oders.boundary;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

public class OrderResourceIT {

	private Client client;
	private WebTarget tut;

	@Before
	public void init() {
		this.client = ClientBuilder.newClient();
		this.tut = this.client.target("http://localhost:8080/sample-service/resources/orders");
	}

	@Test
	public void testOrders() {
		Response response = this.tut.request(MediaType.APPLICATION_JSON).get();
		assertThat(response.getStatus(), is(200));
		JsonObject result = response.readEntity(JsonObject.class);
		assertNotNull(result);
		System.out.println(result);
	}
}
