package de.rieckpil.learning.keycloakexample;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.security.Principal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Controller
public class PageController {

    private List<Customer> customerList;

    @PostConstruct
    public void init() {
        customerList = Arrays.asList(
                new Customer(1l, "Jane", "Mainstreet", Instant.now()),
                new Customer(2l, "Bill", "Henkestreet", Instant.MAX),
                new Customer(3l, "Mike", "Citycenter", Instant.MIN));
    }

    @GetMapping
    @RequestMapping("/")
    public String index() {
        return "external";
    }

    @GetMapping
    @RequestMapping("/customers")
    public String customers(Model model, Principal principal) {
        model.addAttribute("customers", this.customerList);
        model.addAttribute("username", principal.getName());
        return "customers";
    }
}
