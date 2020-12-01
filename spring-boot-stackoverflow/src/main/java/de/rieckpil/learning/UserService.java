package de.rieckpil.learning;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class UserService {

  private final EntityManager entityManager;

  public UserService(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public void create(String username) {
    // create the user
  }
}
