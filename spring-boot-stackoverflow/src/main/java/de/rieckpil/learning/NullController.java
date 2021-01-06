package de.rieckpil.learning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/null")
public class NullController {

  @GetMapping
  public String doFoo() {
    return null;
  }
}
