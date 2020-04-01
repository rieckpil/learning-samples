package de.rieckpil.learning;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class SampleController {

  @GetMapping
  public String getData() {
    printCurrentSecurity();
    return "Hello World";
  }

  @GetMapping(value = "/xml", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
  public XmlData xmlData() {
    return new XmlData("Hello World");
  }

  @GetMapping("/secure")
  public String getSecureData() {
    return "Secure Hello World";
  }

  private void printCurrentSecurity() {
    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();

    authentication.getAuthorities().forEach(o -> System.out.println(o));
    System.out.println(authentication.getCredentials());
    System.out.println(authentication.getPrincipal());
  }
}
