package de.rieckpil.learning;

import java.util.UUID;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.QueryLogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringDataCassandraTestApplication implements CommandLineRunner {

  @Autowired
  UserRepository userRepository;

  @Autowired
  UserService    userService;

  @Bean
  public QueryLogger queryLogger(Cluster cluster) {
    QueryLogger queryLogger = QueryLogger.builder().build();
    cluster.register(queryLogger);
    return queryLogger;
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringDataCassandraTestApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    userService.alternativeWay();

  }

  private void insertData() {
    long startingId = userRepository.count();

    if (userRepository.count() < 100_000) {

      for (int i = 1; i <= 100_000; i++) {
        User test = new User();
        test.setCode(UUID.randomUUID().toString());
        test.setFirstname("Max");
        test.setLastname("Mustermann");
        test.setId(startingId + i);
        userRepository.save(test);
      }

      System.out.println("Finished inserting 100.000 entries");

    }
  }
}
