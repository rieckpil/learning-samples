package de.rieckpil.learning.user;

import com.google.common.collect.ImmutableSet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
  User createUser(CreateUserParameters parameters);

  ImmutableSet<User> getAllUsers();

  Page<User> getUsers(Pageable pageable);

  boolean userWithEmailExists(Email email);
}
