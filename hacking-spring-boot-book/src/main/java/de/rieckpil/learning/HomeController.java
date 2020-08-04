package de.rieckpil.learning;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {

  private ItemRepository itemRepository;
  private CartRepository cartRepository;
  private InventoryService inventoryService;

  public HomeController(ItemRepository itemRepository, CartRepository cartRepository,
                        InventoryService inventoryService) {
    this.itemRepository = itemRepository;
    this.cartRepository = cartRepository;
    this.inventoryService = inventoryService;
  }

  @GetMapping
  Mono<Rendering> home() {
    return Mono.just(Rendering.view("home.html")
      .modelAttribute("items", this.itemRepository.findAll().doOnNext(System.out::println))
      .modelAttribute("cart", this.cartRepository.findById("My Cart").defaultIfEmpty(new Cart("My Cart")))
      .build());
  }

  @PostMapping("/add/{id}")
  Mono<String> addToCart(@PathVariable String id) {
    return this.cartRepository.findById("My Cart")
      .defaultIfEmpty(new Cart("My Cart"))
      .flatMap(cart -> cart.getCartItems().stream()
        .filter(cartItem -> cartItem.getItem()
          .getId().equals(id))
        .findAny()
        .map(cartItem -> {
          cartItem.increment();
          return Mono.just(cart);
        }).orElseGet(() -> {
          return this.itemRepository.findById(id)
            .map(item -> new CartItem(item))
            .map(cartItem -> {
              cart.getCartItems().add(cartItem);
              return cart;
            });
        }))
      .flatMap(cart -> this.cartRepository.save(cart))
      .thenReturn("redirect:/");
  }

  @PostMapping
  Mono<String> createItem(@ModelAttribute Item newItem) {
    return this.itemRepository.save(newItem)
      .thenReturn("redirect:/");
  }

  @DeleteMapping("/delete/{id}")
  Mono<String> deleteItem(@PathVariable String id) {
    return this.itemRepository.deleteById(id)
      .thenReturn("redirect:/");
  }

  @GetMapping("/search")
  Mono<Rendering> search( //
                          @RequestParam(required = false) String name,
                          @RequestParam(required = false) String description,
                          @RequestParam boolean useAnd) {
    return Mono.just(Rendering.view("home.html")
      .modelAttribute("results",
        inventoryService.searchByExample(name, description, useAnd))
      .build());
  }
}
