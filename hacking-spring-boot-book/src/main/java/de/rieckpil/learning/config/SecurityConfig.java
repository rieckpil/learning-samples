package de.rieckpil.learning.config;

import de.rieckpil.learning.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableReactiveMethodSecurity
public class SecurityConfig {

  static final String USER = "USER";
  static final String INVENTORY = "INVENTORY";

  @Bean
  public ReactiveUserDetailsService userDetailsService(UserRepository userRepository) {
    return username -> userRepository.findByName(username)
      .map(user -> User.withDefaultPasswordEncoder()
        .username(user.getName())
        .password(user.getPassword())
        .authorities(user.getRoles().toArray(new String[0]))
        .build());
  }

  @Bean
  SecurityWebFilterChain customPolicy(ServerHttpSecurity httpSecurity) {
    return httpSecurity
      .authorizeExchange(exchanges -> exchanges
        .pathMatchers(HttpMethod.POST, "/").hasRole(INVENTORY)
        .pathMatchers(HttpMethod.DELETE, "/**").hasRole(INVENTORY)
        .anyExchange().authenticated()
        .and()
        .httpBasic()
        .and()
        .formLogin())
      .csrf().disable()
      .build();
  }
}
