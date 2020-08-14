package de.rieckpil.learning;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.Arrays;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    //BlockHound.builder()
    //  .allowBlockingCallsInside(TemplateEngine.class.getCanonicalName(), "process")
    //  .install();

    SpringApplication.run(Application.class, args);
  }

  @Bean
  CommandLineRunner userLoader(MongoOperations mongoOperations) {
    return args -> {
      mongoOperations.save(new User("rieckpil", "duke", Arrays.asList("ROLE_ADMIN")));
      mongoOperations.save(new User("duke", "duke", Arrays.asList("ROLE_ADMIN", "ROLE_INVENTORY")));
    };
  }

  @Bean
  Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public HttpTraceRepository httpTraceRepository() {
    return new InMemoryHttpTraceRepository();
  }

}
