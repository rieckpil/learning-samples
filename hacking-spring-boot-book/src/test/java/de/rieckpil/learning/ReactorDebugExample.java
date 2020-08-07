package de.rieckpil.learning;

import org.junit.jupiter.api.RepeatedTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Random;

public class ReactorDebugExample {

  @RepeatedTest(3)
  public void testDebug() {

    Hooks.onOperatorDebug();

    Mono<Integer> source;

    if (new Random().nextBoolean()) {
      source = Flux.range(1, 10).elementAt(5);
    } else {
      source = Flux.just(1, 2, 3, 4).elementAt(5);
    }

    source
      .log("beforeSubscribe")
      .subscribeOn(Schedulers.parallel())
      .log("afterSubscribe")
      .block();
  }

}
