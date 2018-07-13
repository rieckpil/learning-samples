package de.rieckpil.learning.user.control;

import de.rieckpil.learning.user.entity.JpaUser;
import de.rieckpil.learning.user.entity.User;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

public class UserFactory {

    @Produces
    @Default
    public User getUser() {
        return new User("rieckpil", "my@mail.de");
    }
}
