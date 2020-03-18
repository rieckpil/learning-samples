package de.rieckpil.learning;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/sample")
public class SampleController {

  @GetMapping
  public String getData() {
    printCurrentSecurity();
    return "Hello World";
  }

  @GetMapping("/secure")
  public String getSecureData(Principal principal) {

    UserDetails userDetails= (UserDetails) principal;
    System.out.println(userDetails.getUsername());
    System.out.println(userDetails.getPassword());
    System.out.println(userDetails.getAuthorities());

    printCurrentSecurity();
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
