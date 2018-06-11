package de.rieckpil.learning.testcontainerssample;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestcontainersSampleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonBoundaryAllTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer().withPassword("inmemory")
            .withUsername("inmemory");

    @LocalServerPort
    private int localPort;

    public TestRestTemplate testRestTemplate = new TestRestTemplate();

    @BeforeClass
    public static void beforeClass() {

        System.out.println(postgreSQLContainer.getJdbcUrl());
        System.setProperty("spring.datasource.url", postgreSQLContainer.getJdbcUrl());
        System.setProperty("spring.datasource.password", postgreSQLContainer.getPassword());
        System.setProperty("spring.datasource.username", postgreSQLContainer.getUsername());

    }


    @Test
    @Sql("/insertPersons.sql")
    public void testRestEndpointForAllPersons() {

        ResponseEntity<Person[]> result = testRestTemplate.getForEntity("http://localhost:" + localPort +
                        "/persons", Person[]
                        .class);

        System.out.println(result);

        assertNotNull(result);
        assertThat(result.getBody().length, is(4));

    }
}
