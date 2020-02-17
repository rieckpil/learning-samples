package de.rieckpil;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InvoiceService {

  List<Invoice> invoices = new CopyOnWriteArrayList<>();

  public List<Invoice> findAll() {
    return invoices;
  }

  public Invoice create(String userId, Integer amount) {
    User user = Application.INSTANCE.getUserService().findById(userId);
    if (user == null) {
      throw new IllegalStateException();
    }

    Invoice invoice = new Invoice(user.getId(), "http://www.africau.edu/images/default/sample.pdf");
    invoices.add(invoice);
    return invoice;
  }

}
