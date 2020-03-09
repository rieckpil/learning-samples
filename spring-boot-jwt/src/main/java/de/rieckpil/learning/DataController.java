package de.rieckpil.learning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataController {

  @GetMapping
  public String sayHello() {
    return "Hello World";
  }
}
