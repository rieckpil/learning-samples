package de.rieckpil.learning.springboot2book.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserEntity {

    private String username;
    private String hashedPassword;
}
