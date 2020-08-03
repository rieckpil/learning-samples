package de.rieckpil.learning;

import org.springframework.data.annotation.Id;

import java.util.Objects;

public class Item {

  @Id
  private String id;
  private String name;
  private String description;
  private double price;
  // end::code[]

  private Item() {
  }

  Item(String name, String description, double price) {
    this.name = name;
    this.description = description;
    this.price = price;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Item item = (Item) o;
    return Double.compare(item.price, price) == 0 && Objects.equals(id, item.id) && Objects.equals(name, item.name)
      && Objects.equals(description, item.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, price);
  }

  @Override
  public String toString() {
    return "Item{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", description='" + description + '\'' + ", price="
      + price + '}';
  }
}
