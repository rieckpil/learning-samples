package de.rieckpil.learning.user;

import com.google.common.collect.ImmutableSet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
  private final UserRepository repository;

  public UserServiceImpl(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public User createUser(CreateUserParameters parameters) {
    UserId userId = repository.nextId();
    User user = new User(userId,
      parameters.getUserName(),
      parameters.getGender(),
      parameters.getBirthday(),
      parameters.getEmail(),
      parameters.getPhoneNumber());
    return repository.save(user);
  }

  @Override
  public ImmutableSet<User> getAllUsers() {
    return ImmutableSet.copyOf(repository.findAll());
  }

  @Override
  public Page<User> getUsers(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @Override
  public User editUser(UserId userId, EditUsersParameters parameters) {
    User user = repository.findById(userId)
      .orElseThrow(() -> new UserNotFoundException(userId));

    if (parameters.getVersion() != user.getVersion()) {
      throw new ObjectOptimisticLockingFailureException(User.class, user.
        getId().asString());
    }
    parameters.update(user);
    return user;
  }

  @Override
  public boolean userWithEmailExists(Email email) {
    return repository.existsByEmail(email);
  }

  @Override
  public Optional<User> getUser(UserId userId) {
    return repository.findById(userId);
  }
}
