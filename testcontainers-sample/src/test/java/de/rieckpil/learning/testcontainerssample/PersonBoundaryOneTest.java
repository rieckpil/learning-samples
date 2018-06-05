package de.rieckpil.learning.testcontainerssample;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
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
@ContextConfiguration(initializers = PersonBoundaryOneTest.Initializer.class)
public class PersonBoundaryOneTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer().withPassword("inmemory")
            .withUsername("inmemory");

    @LocalServerPort
    private int localPort;

    public TestRestTemplate testRestTemplate = new TestRestTemplate();

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {

            System.out.println("##########" + postgreSQLContainer.getJdbcUrl());

            TestPropertyValues values = TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername()
            );

            values.applyTo(configurableApplicationContext);
        }
    }

    @Test
    @Sql("/insertPersons.sql")
    public void testRestEndpointForOnePersonWhichExists() {

        ResponseEntity<Person> result = testRestTemplate.getForEntity("http://localhost:" + localPort +
                        "/persons/1",
                Person
                        .class);

        System.out.println(result);

        assertNotNull(result);
        assertNotNull(result.getBody());

    }

    @Test
    public void testRestEndpointForOnePersonWhichDoesNotExist() {

        ResponseEntity<Person> result = testRestTemplate.getForEntity("http://localhost:" + localPort +
                        "/persons/6",
                Person
                        .class);

        System.out.println(result);

        assertNotNull(result);
        assertThat(result.getStatusCode(), is(HttpStatus.NOT_FOUND));

    }

}
