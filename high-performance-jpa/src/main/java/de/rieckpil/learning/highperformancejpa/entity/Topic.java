package de.rieckpil.learning.highperformancejpa.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String owner;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;
}
