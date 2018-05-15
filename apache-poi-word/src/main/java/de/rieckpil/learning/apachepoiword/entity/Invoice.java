package de.rieckpil.learning.apachepoiword.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    private String name;
    private String age;
    private Instant date;

}
