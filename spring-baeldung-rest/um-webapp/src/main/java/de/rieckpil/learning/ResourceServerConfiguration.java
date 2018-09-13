package de.rieckpil.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableResourceServer
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		return authProvider;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.authorizeRequests()
				.anyRequest().authenticated()
			.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.csrf().disable();
		// @formatter:on
	}

}
