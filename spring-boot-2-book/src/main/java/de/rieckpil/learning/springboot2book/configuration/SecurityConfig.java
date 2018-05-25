package de.rieckpil.learning.springboot2book.configuration;

import de.rieckpil.learning.springboot2book.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /*@Bean
    public UserDetailsService userDetailsService(final UserRepository userRepository) {

        return username -> userRepository.findOneByLogin(username).map(userEntity ->
            new User(
                    userEntity.getUsername(),
                    userEntity.getHashedPassword(),
                    new ArrayList<>())
        ).orElseThrow(() -> new UsernameNotFoundException(username));

    }*/

}
