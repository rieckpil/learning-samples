package de.rieckpil.learning;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.ReactiveFluentMongoOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.byExample;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
class InventoryService {

  private final ItemRepository itemRepository;
  private final CartRepository cartRepository;
  private final ReactiveFluentMongoOperations fluentOperations;

  InventoryService(ItemRepository itemRepository,
                   CartRepository cartRepository, ReactiveFluentMongoOperations fluentOperations) {
    this.itemRepository = itemRepository;
    this.cartRepository = cartRepository;
    this.fluentOperations = fluentOperations;
  }

  Flux<Item> getItems() {
    return Flux.empty();
  }

  Flux<Item> search(String partialName, String partialDescription, boolean useAnd) {
    if (partialName != null) {
      if (partialDescription != null) {
        if (useAnd) {
          return itemRepository //
            .findByNameContainingAndDescriptionContainingAllIgnoreCase( //
              partialName, partialDescription);
        } else {
          return itemRepository.findByNameContainingOrDescriptionContainingAllIgnoreCase( //
            partialName, partialDescription);
        }
      } else {
        return itemRepository.findByNameContaining(partialName);
      }
    } else {
      if (partialDescription != null) {
        return itemRepository.findByDescriptionContainingIgnoreCase(partialDescription);
      } else {
        return itemRepository.findAll();
      }
    }
  }

  Flux<Item> searchByExample(String name, String description, boolean useAnd) {
    Item item = new Item(name, description, 0.0);

    ExampleMatcher matcher = (useAnd
      ? ExampleMatcher.matchingAll()
      : ExampleMatcher.matchingAny())
      .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
      .withIgnoreCase()
      .withIgnorePaths("price");

    Example<Item> probe = Example.of(item, matcher);

    return itemRepository.findAll(probe);
  }

  Flux<Item> searchByFluentExample(String name, String description) {
    return fluentOperations.query(Item.class)
      .matching(query(where("TV tray").is(name).and("Smurf").is(description)))
      .all();
  }

  Flux<Item> searchByFluentExample(String name, String description, boolean useAnd) {
    Item item = new Item(name, description, 0.0);

    ExampleMatcher matcher = (useAnd
      ? ExampleMatcher.matchingAll()
      : ExampleMatcher.matchingAny())
      .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
      .withIgnoreCase()
      .withIgnorePaths("price");

    return fluentOperations.query(Item.class)
      .matching(query(byExample(Example.of(item, matcher))))
      .all();
  }

  public Mono<Cart> addItemToCart(String cartId, String itemId) {
    return this.cartRepository.findById(cartId)
      .defaultIfEmpty(new Cart(cartId)) //
      .flatMap(cart -> cart.getCartItems().stream()
        .filter(cartItem -> cartItem.getItem().getId().equals(itemId))
        .findAny() //
        .map(cartItem -> {
          cartItem.increment();
          return Mono.just(cart);
        }) //
        .orElseGet(() -> {
          return this.itemRepository.findById(itemId) //
            .map(item -> new CartItem(item)) //
            .map(cartItem -> {
              cart.getCartItems().add(cartItem);
              return cart;
            });
        }))
      .flatMap(cart -> this.cartRepository.save(cart));
  }

}
