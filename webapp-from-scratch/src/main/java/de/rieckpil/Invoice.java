package de.rieckpil;

import java.util.UUID;

class Invoice {

  private String id;
  private String pdfUrl;
  private String userId;

  public Invoice() {
  }

  public Invoice(String userId, String pdfUrl) { // (1)
    this.id = UUID.randomUUID().toString();  // (1)
    this.pdfUrl = pdfUrl;
    this.userId = userId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPdfUrl() {
    return pdfUrl;
  }

  public void setPdfUrl(String pdfUrl) {
    this.pdfUrl = pdfUrl;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
