package de.rieckpil.learning.reservations.control;

import de.rieckpil.learning.reservations.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
