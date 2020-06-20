package de.rieckpl.learning.ws;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public String greeting(String message) {
    System.out.println(message);
    System.out.println(SecurityContextHolder.getContext().getAuthentication());

    if (SecurityContextHolder.getContext().getAuthentication() != null) {
      System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
    }

    return "Hello, " + HtmlUtils.htmlEscape(message) + "!";
  }

}
