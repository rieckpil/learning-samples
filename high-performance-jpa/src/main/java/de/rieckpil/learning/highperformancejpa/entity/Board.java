package de.rieckpil.learning.highperformancejpa.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "board")
@Data
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
