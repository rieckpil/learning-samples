package de.rieckpil.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@ServletComponentScan
// @EnableScheduling
@SpringBootApplication
public class Application {

  @Bean
  public MyInterface myInterface() {
    return () -> "Hello";
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
