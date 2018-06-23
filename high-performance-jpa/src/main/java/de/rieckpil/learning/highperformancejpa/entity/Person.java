package de.rieckpil.learning.highperformancejpa.entity;

import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Data
@NamedQuery(name = "findAllPersons", query = "SELECT p FROM Person p")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
