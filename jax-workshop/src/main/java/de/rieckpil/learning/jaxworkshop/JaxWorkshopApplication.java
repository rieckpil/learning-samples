package de.rieckpil.learning.jaxworkshop;

import de.rieckpil.learning.jaxworkshop.user.control.UserTestDataLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JaxWorkshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(JaxWorkshopApplication.class, args);
    }

    @Bean(initMethod = "loadData")
    UserTestDataLoader RepositoryTestData() {
        return new UserTestDataLoader();
    }
}
