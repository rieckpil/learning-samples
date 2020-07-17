package de.rieckpil.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ServletComponentScan
@EnableScheduling
@SpringBootApplication
public class SpringBootStackoverflowApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootStackoverflowApplication.class, args);
  }

}
