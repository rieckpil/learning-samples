package de.rieckpil.learning.apachepoiword.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Property {

    @JsonProperty("marktWert")
    private int marktwert;

    @JsonProperty("someValue")
    private int wasgeht;

}
