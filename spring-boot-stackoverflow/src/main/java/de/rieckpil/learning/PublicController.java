package de.rieckpil.learning;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/public")
public class PublicController {

  private final EntityManager entityManager;
  private final Environment environment;
  private final MeterRegistry meterRegistry;

  public PublicController(EntityManager entityManager, Environment environment, MeterRegistry meterRegistry) {
    this.entityManager = entityManager;
    this.environment = environment;
    this.meterRegistry = meterRegistry;
  }

  @GetMapping
  public String sayHello() {
    meterRegistry.counter("duke", Tags.of("enabled", String.valueOf(ThreadLocalRandom.current().nextBoolean()))).increment();
    return "Hello";
  }

  @PutMapping
  public String sayHelloPut(String content) {
    return content.toUpperCase();
  }

  @GetMapping("/data")
  public Object returnFoo(Model model) {

    String envValue = environment.getProperty("debug");
    boolean isDebugMode = (envValue != null && !envValue.equals("false"));
    model.addAttribute("DEBUG", isDebugMode);

    System.out.println(isDebugMode);

    Map<String, Object> resultFromDatabase = Map.of("name", "duke", "data",
      Map.of("id", 1337, "enabled", true));
    return resultFromDatabase;
  }
}
