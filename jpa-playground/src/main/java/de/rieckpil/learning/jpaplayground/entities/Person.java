package de.rieckpil.learning.jpaplayground.entities;

import de.rieckpil.learning.jpaplayground.auditing.AuditedEntity;
import de.rieckpil.learning.jpaplayground.entities.Address;
import de.rieckpil.learning.jpaplayground.entities.Passport;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Audited
@Data
public class Person extends AuditedEntity {

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
