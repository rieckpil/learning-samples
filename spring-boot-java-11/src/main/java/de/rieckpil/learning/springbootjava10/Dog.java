package de.rieckpil.learning.springbootjava10;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Component
@Validated
public class Dog {
  private String name;

  public void setName(@NotNull String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}
