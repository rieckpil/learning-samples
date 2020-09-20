package de.rieckpil.learning;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import io.github.sivalabs.localstack.EnableLocalStack;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableLocalStack
@SpringBootApplication
public class LocalstackSivasDemoApplication implements CommandLineRunner {

  private final AmazonSQSAsync amazonSQSAsync;

  private final AmazonS3 amazonS3;

  public LocalstackSivasDemoApplication(AmazonS3 amazonS3, AmazonSQSAsync amazonSQSAsync) {
    this.amazonS3 = amazonS3;
    this.amazonSQSAsync = amazonSQSAsync;
  }

  public static void main(String[] args) {
    SpringApplication.run(LocalstackSivasDemoApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Started");
  }
}
