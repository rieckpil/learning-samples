package de.rieckpil.test.springbootjava13;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/json")
public class SampleController {

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public String getJson() {
    return """
          {
            "name":"duke",
            "age": 42
          }
      """;
  }
}
