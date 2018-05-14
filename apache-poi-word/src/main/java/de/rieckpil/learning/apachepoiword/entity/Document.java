package de.rieckpil.learning.apachepoiword.entity;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.List;

public class Document {

    private Instant instant;
    private LocalDate localDate;
    private LocalDateTime localDateTime;

    public static void main(String[] args) {
        Document document1 = new Document(Instant.now(), LocalDate.now(), LocalDateTime.now());
        Document document2 = new Document(Instant.MIN, LocalDate.of(1995, 9,13), LocalDateTime.ofInstant(Instant.now
                (), ZoneId.of("Europe/Helsinki")));
        Document document3 = new Document(Instant.MAX, LocalDate.now(), LocalDateTime.now());
        Document document4 = new Document(Instant.ofEpochMilli(6000000), LocalDate.now(ZoneId.of("Asia/Bangkok")),
                LocalDateTime.now(ZoneId.of("Asia/Bangkok")));

        List<Document> documentList = Arrays.asList(document1, document2, document3, document4);

        for (Document document : documentList) {
            System.out.println(document.toString());
        }

    }

    public Document(Instant instant, LocalDate localDate, LocalDateTime localDateTime) {
        this.instant = instant;
        this.localDate = localDate;
        this.localDateTime = localDateTime;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "Document{" +
                "instant=" + instant +
                ", localDate=" + localDate +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
