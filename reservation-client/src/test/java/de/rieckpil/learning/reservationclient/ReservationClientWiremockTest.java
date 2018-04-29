package de.rieckpil.learning.reservationclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;

@RunWith(SpringRunner.class)
@AutoConfigureWireMock(port = 11111)
@SpringBootTest

public class ReservationClientWiremockTest {

    @Autowired
    private ReservationClient reservationClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllReservations() throws Exception {

        String json = this.objectMapper.writeValueAsString(
                Arrays.asList(new Reservation(1L, "Jane", Instant.now()), new Reservation(2L, "John", Instant.now())));

        WireMock.stubFor(
                WireMock.get("/api/reservations")
                        .willReturn(WireMock.aResponse()
                                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                                .withStatus(200)
                                .withBody(json)
                        ));


        Collection<Reservation> reservations = this.reservationClient.getAllReservations();
        Assertions.assertThat(reservations).isNotNull();
        Assertions.assertThat(reservations.isEmpty()).isFalse();
    }
}
