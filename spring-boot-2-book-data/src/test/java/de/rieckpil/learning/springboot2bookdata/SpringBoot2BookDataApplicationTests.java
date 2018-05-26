package de.rieckpil.learning.springboot2bookdata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("h2")
public class SpringBoot2BookDataApplicationTests {

    @Test
    public void contextLoads() {
    }

}
