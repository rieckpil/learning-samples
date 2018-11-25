package de.rieckpil.learning;

public class Fare {

    private Long nightSurcharge;
    private Long rideFare;

    public Fare() {
    }

    public Fare(Long nightSurcharge, Long rideFare) {
        this.nightSurcharge = nightSurcharge;
        this.rideFare = rideFare;
    }

    public Long getNightSurcharge() {
        return nightSurcharge;
    }

    public void setNightSurcharge(Long nightSurcharge) {
        this.nightSurcharge = nightSurcharge;
    }

    public Long getRideFare() {
        return rideFare;
    }

    public void setRideFare(Long rideFare) {
        this.rideFare = rideFare;
    }

    public Long getTotalFare() {
        return this.nightSurcharge + this.rideFare;
    }
}
