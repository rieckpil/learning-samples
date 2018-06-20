package de.rieckpil.learning.highperformancejpa.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;

@Data
@Entity
@Table(name= "announcement")
public class Announcement extends Topic {

    private Instant validUntil;
}
