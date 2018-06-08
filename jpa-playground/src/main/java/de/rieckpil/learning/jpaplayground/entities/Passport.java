package de.rieckpil.learning.jpaplayground.entities;

import de.rieckpil.learning.jpaplayground.auditing.AuditedEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Passport extends AuditedEntity {

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
