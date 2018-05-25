package de.rieckpil.learning.springboot2book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
public class SecuredController {

    @Autowired
    private SecuredService securedService;

    @GetMapping("/greeting")
    public String getGreeting(final Principal principal) {
        return securedService.greetPrincipal(principal);
    }

    @GetMapping("/greeting-secret")
    public String getSecretGreeting(final Principal principal) {
        return securedService.getSecuredValue();
    }

}
