package de.rieckpil.learning;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SomeService {

  private final MyInterface myInterface;

  public SomeService(MyInterface myInterface) {
    this.myInterface = myInterface;
  }

  public String doFoo() {
    return myInterface.getName();
  }

}
