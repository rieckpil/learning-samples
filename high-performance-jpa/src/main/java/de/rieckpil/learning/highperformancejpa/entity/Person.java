package de.rieckpil.learning.highperformancejpa.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@NamedQuery(name = "findAllPersons", query = "SELECT p FROM Person p")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person() {
    }
}
