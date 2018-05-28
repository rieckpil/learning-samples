package de.rieckpil.learning.jpaplayground;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passportId;

    @OneToOne(mappedBy = "passport", cascade = CascadeType.PERSIST)
    private Person person;

    public void setPerson(Person person, boolean setReference) {

        this.person = person;

        if(setReference) {
            person.setPassport(this, false);
        }

    }
}
