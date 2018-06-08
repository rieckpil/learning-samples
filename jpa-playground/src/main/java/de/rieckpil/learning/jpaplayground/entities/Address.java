package de.rieckpil.learning.jpaplayground.entities;

import de.rieckpil.learning.jpaplayground.auditing.AuditedEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address extends AuditedEntity {

    private String street;

    private String city;

    private int houseNumber;

    @ManyToOne
    private Person person;

}
