package de.rieckpil.learning;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class XmlData {
  private String payload;

  public XmlData(String payload) {
    this.payload = payload;
  }

  public String getPayload() {
    return payload;
  }

  public void setPayload(String payload) {
    this.payload = payload;
  }
}
