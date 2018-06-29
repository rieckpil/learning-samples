package de.rieckpil.learning.streamsandlambdas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String name;
    private Gender gender;
    private int age;
}
