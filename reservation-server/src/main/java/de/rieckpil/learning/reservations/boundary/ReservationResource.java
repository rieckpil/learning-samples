package de.rieckpil.learning.reservations.boundary;

import de.rieckpil.learning.reservations.control.ReservationDTO;
import de.rieckpil.learning.reservations.control.ReservationRepository;
import de.rieckpil.learning.reservations.control.ReservationService;
import de.rieckpil.learning.reservations.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationResource {

    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;

    @Qualifier("dbHealthIndicator")
    @Autowired
    private HealthIndicator dbHealthIndicator;

    public ReservationResource(ReservationRepository reservationRepository, ReservationService reservationService) {
        this.reservationRepository = reservationRepository;
        this.reservationService = reservationService;
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

    @PostMapping
    public ResponseEntity<Void> createNewReservation(@RequestBody @Valid ReservationDTO reservationDTO, UriComponentsBuilder b){

        Long reservationId = reservationService.createNewReservation(reservationDTO);

        UriComponents uriComponents =
                b.path("/api/reservations/{id}").buildAndExpand(reservationId);

        return ResponseEntity.created(uriComponents.toUri()).build();

    }

}
