package de.rieckpil.learning.reservations.control;

import de.rieckpil.learning.reservations.entity.Reservation;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepositor;

    public ReservationService(ReservationRepository reservationRepositor) {
        this.reservationRepositor = reservationRepositor;
    }

    public Long createNewReservation(ReservationDTO reservationDTO) {

        Reservation r = new Reservation();
        r.setCreated(Instant.now());
        r.setReservationName(reservationDTO.getReservationName());

        reservationRepositor.save(r);

        return r.getId();
    }
}
