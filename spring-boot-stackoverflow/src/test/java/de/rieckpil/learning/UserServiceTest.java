package de.rieckpil.learning;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock
  private EntityManager entityManager;

  @InjectMocks
  private UserService userService;

  @Test
  void shouldCreateUser() {
    this.userService.create("duke");
  }

}
