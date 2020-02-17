package de.rieckpil;

import com.fasterxml.jackson.databind.ObjectMapper;

public enum Application {
  INSTANCE;

  private InvoiceService invoiceService = new InvoiceService();
  private ObjectMapper objectMapper = new ObjectMapper();
  private UserService userService = new UserService(); // (1)

  public InvoiceService getInvoiceService() {
    return invoiceService;
  }

  public ObjectMapper getObjectMapper() {
    return objectMapper;
  }

  public UserService getUserService() {  // (2)
    return userService;
  }
}
