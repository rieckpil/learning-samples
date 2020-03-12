package de.rieckpl.learning;

import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.Map;

@RestController
@RequestMapping("/public")
public class PublicController {

  private final EntityManager entityManager;
  private final Environment environment;

  public PublicController(EntityManager entityManager, Environment environment) {
    this.entityManager = entityManager;
    this.environment = environment;
  }

  @GetMapping
  public String sayHello() {
    return "Hello";
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
