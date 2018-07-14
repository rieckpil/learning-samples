package de.rieckpil.learning.user.entity;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbNumberFormat;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import java.time.LocalDate;
import java.util.Date;

public class Message {

    private Long id;

    @JsonbProperty("fullName")
    private String name;

    private String email;

    @JsonbNumberFormat("#0.00")
    private Double privateNumber;

    @JsonbDateFormat("dd.MM.yyyy")
    private LocalDate dateCreated;

    public Message(Long id, String name, String email,
                Double privateNumber, LocalDate dateCreated) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.privateNumber = privateNumber;
        this.dateCreated = dateCreated;
    }

    private Message(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getPrivateNumber() {
        return privateNumber;
    }

    public void setPrivateNumber(Double privateNumber) {
        this.privateNumber = privateNumber;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }
}
