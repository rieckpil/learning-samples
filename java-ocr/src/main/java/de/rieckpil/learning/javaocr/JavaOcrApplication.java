package de.rieckpil.learning.javaocr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class JavaOcrApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaOcrApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplateBuilder().build();
    }
}
