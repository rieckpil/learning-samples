package de.rieckpil.learning;

import org.springframework.data.annotation.Id;

import java.util.List;

public class User {

  @Id
  private String id;

  private String name;
  private String password;
  private List<String> roles;

  private User() {
  }

  public User(String name, String password, List<String> roles) {
    this.name = name;
    this.password = password;
    this.roles = roles;
  }

  public User(String id, String name, String password, List<String> roles) {
    this.id = id;
    this.name = name;
    this.password = password;
    this.roles = roles;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }
}
