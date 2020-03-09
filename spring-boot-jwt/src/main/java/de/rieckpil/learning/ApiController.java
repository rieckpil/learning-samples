package de.rieckpil.learning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

  @GetMapping("/persons")
  public String returnPersons() {
    return "Mike, Duke, John";
  }
}
