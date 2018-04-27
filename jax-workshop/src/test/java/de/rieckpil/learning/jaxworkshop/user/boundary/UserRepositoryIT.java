package de.rieckpil.learning.jaxworkshop.user.boundary;

import de.rieckpil.learning.jaxworkshop.user.entity.User;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.not;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryIT {

    @Autowired
    UserRepository userRepository;

    @Test
    public void shouldFindsAllUsers() {
        List<User> userList = userRepository.findAll();
        assertTrue(userList.size() > 0);
    }

    @Test
    public void shouldCreatesNewUser() {
        Long before = userRepository.count();

        User user = userRepository.save(createUser());

        List<User> result = userRepository.findAll();
        assertThat(result.size(), is(before.intValue() + 1));
        assertThat(result, hasItem(user));
    }

    public static User createUser() {
        User testUser = new User(null, "test_user", "Test", "test@toedter.com");
        return testUser;
    }
}