package de.rieckpil.learning.reservationclient;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;

@Component
public class ReservationClient {

    @PostConstruct
    public void init() {
        // System.out.println(this.getAllReservations().size());
    }

    private final RestTemplate restTemplate;
    private final ParameterizedTypeReference<List<Reservation>> ptr =
            new ParameterizedTypeReference<List<Reservation>>() {
            };

    public ReservationClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Reservation> getAllReservations() {
        return this.restTemplate.exchange("http://localhost:11111/api/reservations", HttpMethod.GET, null, ptr)
                .getBody();
    }

}
