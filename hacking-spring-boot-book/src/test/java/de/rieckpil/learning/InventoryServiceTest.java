package de.rieckpil.learning;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class InventoryServiceTest {

  InventoryService inventoryService;

  @MockBean
  private ItemRepository itemRepository;

  @MockBean
  private CartRepository cartRepository;

  @BeforeEach
  public void init() {
    Item sampleItem = new Item("item1", "TV tray", 19.99);
    CartItem cartItem = new CartItem(sampleItem);
    Cart cart = new Cart("myCart", Collections.singletonList(cartItem));


    when(cartRepository.findById(ArgumentMatchers.anyString())).thenReturn(Mono.empty());
    when(itemRepository.findById(ArgumentMatchers.anyString())).thenReturn(Mono.just(sampleItem));
    when(cartRepository.save(ArgumentMatchers.any(Cart.class))).thenReturn(Mono.just(cart));

    inventoryService = new InventoryService(itemRepository, cartRepository, null);
  }

  @Test
  void addItemToEmptyCartShouldProduceOneCartItem() {
    inventoryService.addItemToCart("My Cart", "item1")
      .as(StepVerifier::create)
      .expectNextMatches(cart -> {
        assertThat(cart.getCartItems()).extracting(CartItem::getQuantity)
          .containsExactlyInAnyOrder(1);

        assertThat(cart.getCartItems()).extracting(CartItem::getItem)
          .containsExactly(new Item("item1", "TV tray", 19.99));

        return true;
      })
      .verifyComplete();
  }

  @Test
  void alternativeWayToTest() {
    StepVerifier.create(
      inventoryService.addItemToCart("My Cart", "item1"))
      .expectNextMatches(cart -> {
        assertThat(cart.getCartItems()).extracting(CartItem::getQuantity)
          .containsExactlyInAnyOrder(1);

        assertThat(cart.getCartItems()).extracting(CartItem::getItem)
          .containsExactly(new Item("item1", "TV tray", 19.99));

        return true;
      })
      .verifyComplete();
  }
}
