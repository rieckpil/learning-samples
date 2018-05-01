package de.rieckpil.learning.reservationclient;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureStubRunner(ids = "de.rieckpil.learning:reservation-server:+:11111",
        stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class ReservationClientTest {

    @Autowired
    private ReservationClient reservationClient;

    @Test
    public void testGetAllReservations() {
        List<Reservation> reservations = this.reservationClient.getAllReservations();
        Assertions.assertThat(reservations).isNotNull();
        Assertions.assertThat(reservations.isEmpty()).isFalse();
        assertEquals(2, reservations.size());


    }

}