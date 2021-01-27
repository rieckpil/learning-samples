package de.rieckpil.learning.springbootxmlsample;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
  public List<Customer> getCustomers() {
    return Arrays.asList(new Customer("mike"));
  }
}
