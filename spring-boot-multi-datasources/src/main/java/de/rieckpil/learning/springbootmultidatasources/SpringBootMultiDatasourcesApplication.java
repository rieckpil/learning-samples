package de.rieckpil.learning.springbootmultidatasources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootMultiDatasourcesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMultiDatasourcesApplication.class, args);
    }
}
