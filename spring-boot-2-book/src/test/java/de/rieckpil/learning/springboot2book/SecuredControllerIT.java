package de.rieckpil.learning.springboot2book;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
        "example.the-greeting = Hello from Test!"
})
public class SecuredControllerIT {

    private static final Logger log = LoggerFactory.getLogger(SecuredControllerIT.class);

    @Value("${example.the-greeting}")
    private String greeting;

    @LocalServerPort
    private int localServerPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void makeCallWithUser() {

        log.info("Greeting says: {}", greeting);
        log.info("Test uses random port {}", localServerPort);

        assertThat(testRestTemplate.getForObject("/greeting", String.class), is("Hello, Anonymous."));

    }

}
