package de.rieckpil.learning;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/test")
public class DummyController {

  private final ObjectMapper objectMapper;

  public DummyController(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }


  @GetMapping
  public Object sayHello() {

    objectMapper.getRegisteredModuleIds().forEach(System.out::println);

    if (ThreadLocalRandom.current().nextBoolean())
      throw new CustomProblem("TEST");

    return Map.of("name", "duke");
  }
}
