package de.rieckpil.learning.springboottestentitymanager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.persistence.EntityManager;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ApplicationTests {

  @Autowired
  private TestEntityManager testEntityManager;

  @Autowired
  private EntityManager entityManager;

  @Autowired
  private PersonRepository personRepository;

  @Test
  void contextLoads() {

    assertThat(personRepository.count())
      .isEqualTo(4);

    assertThat(testEntityManager.getEntityManager().createQuery("SELECT COUNT(*) FROM Person p", Long.class).getSingleResult())
      .isEqualTo(4L);

    assertThat(testEntityManager.getEntityManager().createNativeQuery("SELECT COUNT(*) FROM Person").getSingleResult())
      .isEqualTo(BigInteger.valueOf(4));

    assertThat(entityManager.createQuery("SELECT COUNT(*) FROM Person p", Long.class).getSingleResult())
      .isEqualTo(4L);

    assertThat(entityManager.createNativeQuery("SELECT COUNT(*) FROM Person").getSingleResult())
      .isEqualTo(BigInteger.valueOf(4));
  }

}
