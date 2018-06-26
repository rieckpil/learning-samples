package de.rieckpil.learning.springboot2junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class HelloWorldControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllPersons() {

        ResponseEntity<Person[]> personResult = restTemplate.getForEntity("http://localhost:" + port + "/persons", Person[].class);

        assertNotNull(personResult.getBody());
        assertEquals(HttpStatus.OK, personResult.getStatusCode());
        assertEquals(2, personResult.getBody().length);
        assertEquals("Philip", personResult.getBody()[0].getFirstName());
        assertEquals("Riecks", personResult.getBody()[0].getLastName());
        assertEquals(LocalDate.of(1995, 9, 13).toEpochDay(), personResult.getBody()[0].getDob().toEpochDay());

    }
}
