package de.rieckpil.learning.jaxworkshop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloWorldControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldGetHelloWorld() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/helloWorld", String.class);
        assertThat(response.getBody(), equalTo("Hello Jax!"));
    }
}
