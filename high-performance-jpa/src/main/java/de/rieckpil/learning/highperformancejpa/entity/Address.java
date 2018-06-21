package de.rieckpil.learning.highperformancejpa.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString(exclude = {"person"})
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    @OneToOne(mappedBy = "address")
    private Person person;
}
