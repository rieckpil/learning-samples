package de.rieckpil.learning;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import de.rieckpil.learning.domain.Item;
import de.rieckpil.learning.domain.Order;

@Service
public class BatchingExample implements CommandLineRunner {

  @Autowired
  private EntityManager em;

  @Override
  @Transactional
  public void run(String... args) throws Exception {

    this.em.unwrap(Session.class).setJdbcBatchSize(20);

    for (int i = 0; i < 20; i++) {

      Order order = new Order(UUID.randomUUID().toString());
      for (int j = 0; j < ThreadLocalRandom.current().nextInt(50) + 1; j++) {
        order.addItem(new Item(ThreadLocalRandom.current().nextLong(), UUID.randomUUID().toString()));
      }

      this.em.persist(order);
    }

  }

}
