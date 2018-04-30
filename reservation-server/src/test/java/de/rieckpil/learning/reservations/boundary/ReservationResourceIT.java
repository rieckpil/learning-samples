package de.rieckpil.learning.reservations.boundary;

import de.rieckpil.learning.reservations.control.ReservationDTO;
import de.rieckpil.learning.reservations.entity.Reservation;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservationResourceIT {

    @LocalServerPort
    private int serverPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String urlPrefix;
    private final ParameterizedTypeReference<List<Reservation>> ptr =
            new ParameterizedTypeReference<List<Reservation>>() {
            };

    @Before
    public void setUp() throws Exception {
        urlPrefix = "http://localhost:" + serverPort;
    }

    @Test
    @Sql("/insertTestReservations.sql")
    public void getAllReservations() {

        ResponseEntity<List<Reservation>> result = testRestTemplate.exchange(urlPrefix + "/api/reservations",
                HttpMethod.GET, null, ptr);

        for (Reservation r : result.getBody()) {
            System.out.println("r.toString() = " + r.toString());

        }

    }

    @Test
    public void getAllReservationsSecondTry() {

        ResponseEntity<List<Reservation>> result = testRestTemplate.exchange(urlPrefix + "/api/reservations",
                HttpMethod.GET, null, ptr);

        System.out.println("result.getBody().size() = " + result.getBody().size());
    }

    @Test
    public void createNewReservationAndQueryForIt() {

        String reservationName = "New Reservation";
        ReservationDTO reservationDTO = new ReservationDTO(reservationName);

        HttpEntity<ReservationDTO> requestEntity = new HttpEntity<>(reservationDTO);

        ResponseEntity<Void> resultFromCreation = testRestTemplate.exchange(urlPrefix + "/api/reservations", HttpMethod.POST,
                requestEntity, Void.class);

        String urlOfNewReservation = resultFromCreation.getHeaders().getLocation().toString();

        ResponseEntity<Reservation> result = testRestTemplate.exchange(urlOfNewReservation,
                HttpMethod.GET, null, Reservation.class);

        assertNotNull(result.getBody().getId());
        assertNotNull(result.getBody().getCreated());
        assertEquals(reservationName, result.getBody().getReservationName());
    }
}