package de.rieckpil.learning;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class BlockHoundExampleTest {

  @Test
  void test() {
    Mono.delay(Duration.ofSeconds(1))
      .flatMap(tick -> {
        try {
          Thread.sleep(10);
          return Mono.just(true);
        } catch (InterruptedException e) {
          return Mono.error(e);
        }
      })
      .as(StepVerifier::create)
      .verifyErrorMatches(throwable -> {
        assertThat(throwable.getMessage()).contains("Blocking call!");
        return true;
      });

  }

}
