package de.rieckpil.learning.springmailpostgrescf;

import javax.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }
}
