package de.rieckpil.learning.ws;

public class WsMessage {

  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "WsMessage{" +
      "message='" + message + '\'' +
      '}';
  }
}
