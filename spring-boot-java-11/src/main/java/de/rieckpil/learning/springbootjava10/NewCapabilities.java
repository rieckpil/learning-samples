package de.rieckpil.learning.springbootjava10;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class NewCapabilities implements CommandLineRunner {
	
	private Logger logger = LoggerFactory.getLogger(NewCapabilities.class);

	// @formatter:off
	@Override
	public void run(String... args) throws Exception {

		System.out.println("Called");

		List<Integer> list = List.of(1, 334, 12, 121, 99, 1, 802);

		list.stream().takeWhile(e -> e < 99).forEach(System.out::println);

		IntStream.iterate(1, n -> n + 1).takeWhile(n -> n < 10).forEach(System.out::println);

		System.out.println(System.getProperty("java.io.tmpdir"));

		File file = File.createTempFile("java11", ".txt");
		writeToFile(new FileWriter(file));

		HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2)
				.connectTimeout(Duration.ofSeconds(2)).build();

		String astroUrl = "http://api.open-notify.org/astros.json";
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(astroUrl)).GET().build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		logger.info("Response status code: " + response.statusCode());
		logger.info("Response headers: " + response.headers());
		logger.info("Response body: " + response.body());

		String output = response.body();

		System.out.println(output);

		System.out.println("Sending async");

		client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body)
				.thenAccept(System.out::println);

		System.out.println("Async requested ...");

		Path path = Files.writeString(Files.createTempFile("test", ".txt"), "test file \nhello 123 \ncontent");
		System.out.println(path);
		String s = Files.readString(path);
		System.out.println(s);

		List<String> listTwo = List.of("apple", "banana", "orange");
		String[] array2 = listTwo.toArray(String[]::new);
		System.out.println(Arrays.toString(array2));

	}

	private void writeToFile(FileWriter writer) {
		try (writer) {
			writer.write("Hello World \n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// @formatter:on

}
