package de.rieckpil.learning.jaxworkshop.user.boundary;

import de.rieckpil.learning.jaxworkshop.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
