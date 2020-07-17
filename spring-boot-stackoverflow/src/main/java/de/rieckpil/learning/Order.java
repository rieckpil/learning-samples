package de.rieckpil.learning;

public class Order {

  private String amountValue;

  public String getAmountValue() {
    return amountValue;
  }

  public void setAmountValue(String amountValue) {
    this.amountValue = amountValue;
  }

  @Override
  public String toString() {
    return "Order{" +
      "amountValue='" + amountValue + '\'' +
      '}';
  }
}
