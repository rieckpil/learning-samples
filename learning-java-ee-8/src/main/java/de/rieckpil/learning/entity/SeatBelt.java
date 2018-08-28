package de.rieckpil.learning.entity;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class SeatBelt {

    @Enumerated(EnumType.STRING)
    private SeatBeltModel seatBeltModel;

    public void open() {

    }

    public void close() {

    }

}
