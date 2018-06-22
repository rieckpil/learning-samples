package de.rieckpil.learning.highperformancejpa.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "attachment")
@Data
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated
    private MediaType mediaType;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;
}
