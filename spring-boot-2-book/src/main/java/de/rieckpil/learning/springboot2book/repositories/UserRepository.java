package de.rieckpil.learning.springboot2book.repositories;

import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import de.rieckpil.learning.springboot2book.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<UserEntity> findOneByLogin(String username) {

        UserEntity user = new UserEntity("Philip",passwordEncoder.encode("hello"));

        return Optional.ofNullable(user);

    }
}
