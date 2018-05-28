package de.rieckpil.learning.jpaplayground;

import javax.persistence.*;

@Entity
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passportId;

    @OneToOne(mappedBy = "passport")
    private Person person;
}
