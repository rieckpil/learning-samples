package de.rieckpil.learning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AmqpItemService {

  private static final Logger LOG = LoggerFactory.getLogger(AmqpItemService.class);

  private final ItemRepository itemRepository;

  public AmqpItemService(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  @RabbitListener(
    ackMode = "MANUAL",
    bindings = @QueueBinding(
      value = @Queue,
      exchange = @Exchange("hacking-spring-boot"),
      key = "new-items-spring-amqp")
  )
  public Mono<Void> processMessage(Item item) {
    LOG.info("Consuming: " + item);
    return this.itemRepository.save(item).then();
  }
}
