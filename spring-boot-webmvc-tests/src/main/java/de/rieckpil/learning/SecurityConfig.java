package de.rieckpil.learning;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests(authorize -> authorize
      .mvcMatchers("/sample", "/sample/xml").permitAll()
      .mvcMatchers("/sample/secure").hasRole("ADMIN")
      .anyRequest().authenticated())
      .cors().disable();
  }
}
