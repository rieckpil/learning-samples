package de.rieckpil.learning.infrastructure.security;

import de.rieckpil.learning.user.Email;
import de.rieckpil.learning.user.User;
import de.rieckpil.learning.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Service
@Transactional(readOnly = true)
public class DatabaseUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public DatabaseUserDetailsService(UserRepository userRepository) { //<.>
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(new Email(username)) //<.>
      .orElseThrow(() -> new UsernameNotFoundException(
        format("User with email %s could not be found", username))); //<.>

    return new ApplicationUserDetails(user); //<.>
  }
}
