package de.rieckpil.learning.springboot2book;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)

public class SecuredServiceTest {

    @Autowired
    private SecuredService securedService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    @WithMockUser(
            username = "test",
            roles = {"USER", "ADMIN"}
    )
    public void testSecretGreetingShouldBeNotVisibleForNotSuperadmins() {
        expectedException.expect(AccessDeniedException.class);
        securedService.getSecuredValue();
    }

    @Test
    @WithMockUser(
            username = "rieckpil",
            roles = {"USER", "ADMIN", "SUPERADMIN"}
    )
    public void testSecretGreetingShouldBeVisibleForNotSuperadmins() {
        String securedValue = securedService.getSecuredValue();
        assertNotNull(securedValue);
    }

}