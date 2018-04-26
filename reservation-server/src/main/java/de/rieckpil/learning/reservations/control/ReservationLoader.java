package de.rieckpil.learning.reservations.control;

import de.rieckpil.learning.reservations.entity.Reservation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;

@Component
public class ReservationLoader implements CommandLineRunner {

    private final ReservationRepository reservationRepository;

    public ReservationLoader(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Reservation r1 = new Reservation(null, "Hilton", Instant.now());
        Reservation r2 = new Reservation(null, "Best Western", Instant.now());
        Reservation r3 = new Reservation(null, "Hotel Alte Post", Instant.now());
        Reservation r4 = new Reservation(null, "Hotel BnB", Instant.now());

        reservationRepository.saveAll(Arrays.asList(r1,r2,r3,r4));

        System.out.println("Reservations in database: " + reservationRepository.findAll().size());
    }
}
