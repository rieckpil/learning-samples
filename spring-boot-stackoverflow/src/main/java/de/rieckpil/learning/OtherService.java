package de.rieckpil.learning;

import org.springframework.stereotype.Service;

@Service
public class OtherService {

  public String doFoo() {
    return "foo";
  }

  public String doBar(String a, String b) {
    return a + b;
  }
}
