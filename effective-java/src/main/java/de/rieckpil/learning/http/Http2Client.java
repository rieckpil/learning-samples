package de.rieckpil.learning.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

public class Http2Client {

	public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

		HttpClient httpClient = HttpClient.newHttpClient();
		System.out.println("HttpClient version is: " + httpClient.version());

		HttpRequest httpRequest = HttpRequest.newBuilder().uri(new URI("https://www.philipriecks.de")).GET().build();
		Map<String, List<String>> headers = httpRequest.headers().map();

		headers.forEach((k, v) -> System.out.println(k + " - " + v));
		HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandler.asString());

		System.out.println(httpResponse.body());

		CompletableFuture<HttpResponse<String>>  httpResponseAsync = httpClient.sendAsync(httpRequest, HttpResponse.BodyHandler.asString());
		
		httpResponseAsync.thenAccept(result -> System.out.println(result.body()));
		
		System.out.println("passed async");

		
	}

}
