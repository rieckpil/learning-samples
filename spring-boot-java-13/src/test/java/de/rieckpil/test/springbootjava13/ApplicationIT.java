package de.rieckpil.test.springbootjava13;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ApplicationIT {

	@Test
	void contextLoads() {
    String json = """
      {"name": "duke"}
    """;

    assertNotNull(json);
	}

}
