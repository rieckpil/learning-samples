package de.rieckpil.learning.springboot2book.configuration;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class CustomHttpSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .httpBasic()
                .and()
                .csrf().disable()
                .headers()
                    .frameOptions().disable()
                    .cacheControl().disable()
                .and()
                .authorizeRequests()
                .requestMatchers(EndpointRequest.to(MetricsEndpoint.class, HealthEndpoint.class))
                    .permitAll()
                .requestMatchers(EndpointRequest.toAnyEndpoint())
                    .authenticated()
                .and()
                .antMatcher("/**")
                .authorizeRequests()
                    .antMatchers("/api/admin/**")
                    .authenticated()
                    .antMatchers("/api/**")
                    .permitAll();

    }
}
