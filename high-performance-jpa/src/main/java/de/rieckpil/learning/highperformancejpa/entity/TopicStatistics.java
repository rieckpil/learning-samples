package de.rieckpil.learning.highperformancejpa.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "topic_statistics")
@Data
public class TopicStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="id")
    @MapsId
    private Topic topic;

    private long views;
}
