package de.rieckpil.learning;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/public")
public class PublicController {

  private final UserService userService;
  private final MeterRegistry meterRegistry;

  public PublicController(UserService userService, MeterRegistry meterRegistry) {
    this.userService = userService;
    this.meterRegistry = meterRegistry;
  }

  @GetMapping
  public String sayHello(HttpServletRequest request, HttpServletResponse response) {
    request.getHeaderNames().asIterator().forEachRemaining(System.out::println);
    System.out.println(request.getMethod());
    System.out.println(response.toString());
    meterRegistry.counter("duke", Tags.of("enabled", String.valueOf(ThreadLocalRandom.current().nextBoolean()))).increment();
    return "Hello";
  }

  @PutMapping
  public String sayHelloPut(String content) {
    return content.toUpperCase();
  }

  @GetMapping("/data")
  public Object returnFoo(Model model) {

    String envValue = "duke53";
    boolean isDebugMode = (envValue != null && !envValue.equals("false"));
    model.addAttribute("DEBUG", isDebugMode);

    System.out.println(isDebugMode);

    Map<String, Object> resultFromDatabase = Map.of("name", "duke", "data",
      Map.of("id", 1337, "enabled", true));
    return resultFromDatabase;
  }
}
