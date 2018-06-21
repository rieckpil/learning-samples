package de.rieckpil.learning.highperformancejpa.projections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonSummary {

    private String name;
    private String street;

}
