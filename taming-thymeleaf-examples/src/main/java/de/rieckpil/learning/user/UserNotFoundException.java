package de.rieckpil.learning.user;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(UserId userId) {
  }
}
