package de.rieckpil.learning

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.dsl.config.builders.servlet.invoke

@Configuration
class SecurityConfig : WebSecurityConfigurerAdapter() {
  override fun configure(http: HttpSecurity?) {
    http {
      authorizeRequests {
        authorize(anyRequest, permitAll)
      }
    }
  }
}
