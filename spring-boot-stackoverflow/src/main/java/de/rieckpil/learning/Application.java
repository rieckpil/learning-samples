package de.rieckpil.learning;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@ServletComponentScan
// @EnableScheduling
@SpringBootApplication
public class Application implements CommandLineRunner {

  private final DataSource dataSource;

  public Application(DataSource dataSource) {
    this.dataSource = dataSource;
  }


  @Bean
  public MyInterface myInterface() {
    return () -> "Hello";
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Connection to database successful: "
      + dataSource.getConnection().getMetaData().getDatabaseProductName());
  }
}
