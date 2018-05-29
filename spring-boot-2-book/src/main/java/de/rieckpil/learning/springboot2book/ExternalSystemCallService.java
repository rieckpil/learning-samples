package de.rieckpil.learning.springboot2book;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ExternalSystemCallService {

    private final RestTemplate restTemplate;

    private ExternalSystemCallService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getRemoteInfo() {

        Map result = this.restTemplate.getForObject("https://biking.michael-simons.eu/api/system/info", Map.class);

        for (Object o : result.keySet()) {
            System.out.println("Key: o.toString() = " + o.toString());

        }

        for (Object o : result.entrySet()) {
            System.out.println("Entry: o.toString() = " + o.toString());

        }
        return (String) result.get("spring-boot.version");

    }
}
