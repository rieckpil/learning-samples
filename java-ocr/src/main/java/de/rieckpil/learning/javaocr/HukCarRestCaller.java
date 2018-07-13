package de.rieckpil.learning.javaocr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Service
public class HukCarRestCaller {

    @Autowired
    private RestTemplate restTemplate;

    @PostConstruct
    public void init() {

        /**HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<JwtApiKey> forObject = restTemplate.exchange("https://www.huk.de/api/auth/webtoken", HttpMethod
                .GET, entity,JwtApiKey
                .class);*/

        String forObject1 = restTemplate.getForObject("https://www.huk.de/api/auth/webtoken", String.class);

        System.out.println(forObject1);
    }


}
