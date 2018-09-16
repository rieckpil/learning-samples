package de.rieckpil.learning.boundary;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.rieckpil.learning.entity.Ping;

@RestController
@RequestMapping("/pings")
public class PingResource {

	@GetMapping
	public List<Ping> getPings() {
		List<Ping> pings = new ArrayList<>();
		pings.add(createPing());
		pings.add(createPing());
		return pings;
	}

	@GetMapping("/async")
	public String getAsync() {
		return "I am async!";
	}

	private Ping createPing() {
		Ping result = new Ping();
		result.setAge(ThreadLocalRandom.current().nextInt(100));
		result.setPrice(ThreadLocalRandom.current().nextDouble(555.55));
		result.setName("Random");
		result.setId(UUID.randomUUID().toString());
		return result;
	}

	@GetMapping("/secured")
	public String sayHello() {
		return "Hello World";
	}
}
