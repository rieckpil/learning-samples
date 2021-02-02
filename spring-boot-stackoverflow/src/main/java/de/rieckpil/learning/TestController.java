package de.rieckpil.learning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tests")
public class TestController {

  private final SomeService someService;

  public TestController(SomeService someService) {
    this.someService = someService;
  }

  @GetMapping
  public String getValue() {
    return this.someService.doFoo();
  }
}
