package de.rieckpil.learning.highperformancejpa.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PostDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String createdBy = "Phil";

    @OneToOne
    private Post post;


}
