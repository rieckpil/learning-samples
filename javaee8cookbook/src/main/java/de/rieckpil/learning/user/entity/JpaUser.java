package de.rieckpil.learning.user.entity;

import javax.persistence.*;

@Entity
@Table(name = "JPA_USER")
public class JpaUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String email;

    public JpaUser(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public JpaUser(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public JpaUser() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
