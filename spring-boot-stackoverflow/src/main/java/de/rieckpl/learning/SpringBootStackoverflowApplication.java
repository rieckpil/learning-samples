package de.rieckpl.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SpringBootStackoverflowApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootStackoverflowApplication.class, args);
  }

}
