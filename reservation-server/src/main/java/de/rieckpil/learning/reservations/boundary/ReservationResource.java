package de.rieckpil.learning.reservations.boundary;

import de.rieckpil.learning.reservations.control.ReservationRepository;
import de.rieckpil.learning.reservations.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationResource {

    private final ReservationRepository reservationRepository;

    @Qualifier("dbHealthIndicator")
    @Autowired
    private HealthIndicator dbHealthIndicator;

    public ReservationResource(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {

        System.out.println("reservationRepository.toString() = " + reservationRepository.toString());
        System.out.println(dbHealthIndicator.health().getStatus());

        return new ResponseEntity<List<Reservation>>(reservationRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable("id") Long id) {
        return new ResponseEntity<Reservation>(reservationRepository.findById(id).get(), HttpStatus.OK);
    }

}
