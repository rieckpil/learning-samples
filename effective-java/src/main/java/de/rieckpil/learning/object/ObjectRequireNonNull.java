package de.rieckpil.learning.object;

import java.util.Objects;

public class ObjectRequireNonNull {

  private String name;

  public static void main(String[] args) {
    ObjectRequireNonNull obj = new ObjectRequireNonNull();
    obj.setName(null);
  }

  public void setName(String name) {
    this.name = Objects.requireNonNull(name, "name must not be null");
    System.out.println(this.name);
  }

}
