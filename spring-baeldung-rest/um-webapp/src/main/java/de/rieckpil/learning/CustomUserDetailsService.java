package de.rieckpil.learning;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public final class CustomUserDetailsService implements UserDetailsService {

	@Override
	public final UserDetails loadUserByUsername(final String username) {
		System.out.println(username);
		return new User(username, "admin", null);
	}

}