package de.rieckpil.learning.keycloakexample;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private long id;
    private String name;
    private String address;
    private Instant serviceRendered;

}
