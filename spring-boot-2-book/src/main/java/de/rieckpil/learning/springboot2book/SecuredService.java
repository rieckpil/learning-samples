package de.rieckpil.learning.springboot2book;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class SecuredService {

    @PreAuthorize("hasRole('SUPERADMIN')")
    public String getSecuredValue() {
        System.out.println("User with SUPERADMIM role accessed secured space");
        return "Foo, bar";
    }

    public String greetPrincipal(Principal principal) {
        return String.format("Hello, %s.", Optional.ofNullable(principal).map(Principal::getName).orElse("Anonymous"));
    }
}
