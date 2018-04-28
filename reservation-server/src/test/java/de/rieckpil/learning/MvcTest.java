package de.rieckpil.learning;

import de.rieckpil.learning.reservations.boundary.ReservationResource;
import de.rieckpil.learning.reservations.control.ReservationRepository;
import de.rieckpil.learning.reservations.entity.Reservation;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MvcTest {

    @MockBean
    private ReservationRepository mockedReservationRepository;

    @Autowired
    private ReservationResource reservationResource;

    @Before
    public void setup() {

        Reservation r1 = new Reservation();
        r1.setCreated(Instant.ofEpochMilli(1000000));
        r1.setId(1L);
        r1.setReservationName("ReservationOne");

        Reservation r2 = new Reservation();
        r2.setCreated(Instant.ofEpochMilli(1000000));
        r2.setId(2L);
        r2.setReservationName("ReservationTwo");

        when(mockedReservationRepository.findAll()).thenReturn(Arrays.asList(r1, r2));

        RestAssuredMockMvc.standaloneSetup(reservationResource);
    }

    @Test
    public void test(){
        assertEquals(10, 5*2);
    }

}
