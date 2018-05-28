package de.rieckpil.learning.jpaplayground;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "person")
    private List<Address> addressList = new ArrayList<Address>();

    @OneToOne(cascade = CascadeType.PERSIST)
    private Passport passport;

    public void setPassport(Passport passport, boolean setReference) {

        this.passport = passport;

        if(setReference) {
            passport.setPerson(this, false);
        }

    }
}
