package de.rieckpil.learning.reservationclient;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@Component
public class ReservationClient {

    private final RestTemplate restTemplate;
    private final ParameterizedTypeReference<Collection<Reservation>> ptr =
            new ParameterizedTypeReference<Collection<Reservation>>() {};

    public ReservationClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

}
