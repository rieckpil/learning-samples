package de.rieckpil.learning;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.net.URI;

@RestController
@RequestMapping("/amqp")
public class SpringAmqpItemController {

  private final AmqpTemplate amqpTemplate;

  public SpringAmqpItemController(AmqpTemplate amqpTemplate) {
    this.amqpTemplate = amqpTemplate;
  }

  @PostMapping("/items")
  Mono<ResponseEntity<Void>> addNewItem(@RequestBody Mono<Item> item) {
    System.out.println(item.toString());
    return item
      .publishOn(Schedulers.boundedElastic())
      .flatMap(content -> Mono
        .fromCallable(() -> {
          this.amqpTemplate.convertAndSend("hacking-spring-boot", "new-items-spring-amqp", content);
          return ResponseEntity.created(URI.create("/amqp/items")).build();
        }));
  }

}
