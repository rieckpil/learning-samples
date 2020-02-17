package de.rieckpil;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MyServlet extends HttpServlet {

  private InvoiceService invoiceService = new InvoiceService();  // (1)
  private ObjectMapper objectMapper = new ObjectMapper(); // (1)

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    if (req.getRequestURI().equalsIgnoreCase("/")) {
      resp.getWriter().print("<html>\n" +
        "<body>\n" +
        "<h1>This is my HTML page</h1>\n" +
        "<p> hallo what is going on!!!</p>\n" +
        "</body>\n" +
        "</html>");
    } else if (req.getRequestURI().equalsIgnoreCase("/invoices")) {
      resp.setContentType("application/json; charset=UTF-8"); // (1)
      List<Invoice> invoices = invoiceService.findAll();  // (2)
      resp.getWriter().print(objectMapper.writeValueAsString(invoices));
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    if (req.getRequestURI().equalsIgnoreCase("/invoices")) {
      String userId = req.getParameter("userId");
      Integer amount = Integer.valueOf(req.getParameter("amount"));
      Invoice invoice = invoiceService.create(userId, amount);  // (4)
      resp.setContentType("application/json; charset=UTF-8");
      resp.getWriter().print(objectMapper.writeValueAsString(invoice));  // (5)
    } else {
      throw new IllegalStateException("no url  mapped");
    }
  }
}
