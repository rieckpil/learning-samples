package de.rieckpl.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.Map;

@RestController
@RequestMapping("/public")
public class PublicController {

  private final EntityManager entityManager;

  public PublicController(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @GetMapping
  public String sayHello() {
    return "Hello";
  }

  @GetMapping("/data")
  public Object returnFoo() {
    Map<String, Object> resultFromDatabase = Map.of("name", "duke", "data",
      Map.of("id", 1337, "enabled", true));
    return resultFromDatabase;
  }
}
