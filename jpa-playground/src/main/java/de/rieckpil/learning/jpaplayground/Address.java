package de.rieckpil.learning.jpaplayground;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private String city;

    private int houseNumber;

    @ManyToOne
    private Person person;

}
