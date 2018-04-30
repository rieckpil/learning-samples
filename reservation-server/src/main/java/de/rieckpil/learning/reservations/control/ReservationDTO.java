package de.rieckpil.learning.reservations.control;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

    @NotEmpty
    private String reservationName;

}
