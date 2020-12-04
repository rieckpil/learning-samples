package de.rieckpil.learning.user;

import com.google.common.collect.ImmutableSet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
  User createUser(CreateUserParameters parameters);

  ImmutableSet<User> getAllUsers();

  Page<User> getUsers(Pageable pageable);

  User editUser(UserId userId, EditUsersParameters parameters);

  boolean userWithEmailExists(Email email);

  Optional<User> getUser(UserId userId);

}
