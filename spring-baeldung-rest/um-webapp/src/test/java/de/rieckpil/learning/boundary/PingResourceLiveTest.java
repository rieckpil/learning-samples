package de.rieckpil.learning.boundary;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@RunWith(SpringRunner.class)
public class PingResourceLiveTest {

	@Test
	public void testGetCallToPingsShouldGet200() {

		final String uri = "http://localhost:8080/um-webapp/pings";
		final RequestSpecification spec = RestAssured.given().auth().preemptive().basic("philip", "secret");
		final Response response = spec.given().accept(ContentType.JSON).get(uri);

		System.out.println(response.getBody().asString());

		Assert.assertThat(response.getStatusCode(), Matchers.equalTo(200));
	}

	@Test
	public void testGetCallToPingsShouldGetTwoPings() {

		final String uri = "http://localhost:8080/um-webapp/pings";
		final RequestSpecification spec = RestAssured.given().auth().preemptive().basic("philip", "secret");
		final Response response = spec.given().accept(ContentType.JSON).get(uri);

		List pings = response.as(List.class);

		Assert.assertNotNull(pings);
	}

}
