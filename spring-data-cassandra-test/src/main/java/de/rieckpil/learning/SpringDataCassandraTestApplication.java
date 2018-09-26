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
  TestRepository testRepository;

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

    long startingId = testRepository.count();

    if (testRepository.count() < 500_000) {

      for (int i = 1; i <= 50_000; i++) {
        Test test = new Test();
        test.setCode(UUID.randomUUID().toString());
        test.setFirstname("Max");
        test.setLastname("Mustermann");
        test.setId(startingId + i);
        testRepository.save(test);
      }

      System.out.println("Finished inserting 50.000 entries");

    }

  }
}
