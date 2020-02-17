package de.rieckpil;

public class UserService {

  public User findById(String id) {
    return new User(id, "john"); // always finds the user, every user is called john
  }
}
