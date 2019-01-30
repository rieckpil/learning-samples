package de.rieckpil.learning;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.rieckpil.learning.domain.Item;
import de.rieckpil.learning.domain.Order;

@RestController
@RequestMapping("/orders")
public class OrdersResource {

  @Autowired
  private OrderRepository repository;

  @Autowired
  private EntityManager   em;

  @GetMapping
  @Transactional
  public List<Order> doFoo() {

    List<Order> orders = em.createNamedQuery("allOrders", Order.class).getResultList();

    Item i = new Item();
    i.setId(1337L);
    i.setName("ITEM");

    Order o2 = new Order();
    o2.setNumber("O2");

    Order merge = em.merge(o2);

    em.merge(i);

    em.flush();

    Order o = new Order();
    o.setId(merge.getId());
    o.setNumber("o3");

    Item i2 = new Item();
    i2.setId(1338L);
    i2.setName("ITEM2");

    o.addItem(i2);

    Order mergedOrder = em.merge(o);



    return null;
  }

}
