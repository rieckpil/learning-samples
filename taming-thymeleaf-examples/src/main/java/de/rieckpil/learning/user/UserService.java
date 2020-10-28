package de.rieckpil.learning.user;

import com.google.common.collect.ImmutableSet;

public interface UserService {
  User createUser(CreateUserParameters parameters);

  // ImmutableSet<User> getAllUsers();
}
