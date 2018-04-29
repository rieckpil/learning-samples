package de.rieckpil.learning.reservationclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    private Long id;
    private String reservationName;
    private Instant created;

}
