package de.rieckpl.learning.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class PersonRepositoryTest {

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private TestEntityManager testEntityManager;

  @Test
  public void testOne() {
    System.out.println("####### " + testEntityManager.toString());
    assertEquals(0, personRepository.findAll().size());

    Person person = testEntityManager.persistFlushFind(new Person("Duke"));
    assertNotNull(personRepository.findById(1L));
    assertEquals(person, personRepository.findById(1L).get());
  }

  @Test
  @Sql("/insert_person.sql")
  public void testSql() {
    System.out.println("####### " + testEntityManager.toString());
    assertNotNull(personRepository.findById(1L));
  }

  @Test
  public void testTwo() {
    System.out.println("####### " + testEntityManager.toString());
    assertEquals(0, personRepository.findAll().size());

    personRepository.save(new Person("Duke"));
    assertNotNull(personRepository.findById(1L));
  }

  @Test
  public void testThree() {
    System.out.println("####### " + testEntityManager.toString());
    assertEquals(0, personRepository.findAll().size());

    testEntityManager.persistAndFlush(new Person("Duke"));
    // testEntityManager.clear();
    assertNotNull(personRepository.findById(1L));
  }

  @Test
  @Sql("/insert_person.sql")
  public void testFour() {
    System.out.println("####### " + testEntityManager.toString());

    assertNotNull(personRepository.findById(1L));
  }

}
