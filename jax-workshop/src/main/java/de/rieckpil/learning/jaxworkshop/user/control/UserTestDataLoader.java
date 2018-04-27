package de.rieckpil.learning.jaxworkshop.user.control;

import de.rieckpil.learning.jaxworkshop.user.boundary.UserRepository;
import de.rieckpil.learning.jaxworkshop.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

public class UserTestDataLoader {

    private final Logger logger = LoggerFactory.getLogger(UserTestDataLoader.class);

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void loadData() {

        // TODO use other database

        logger.info("init test data");

        User toedter_k = new User(null,"toedter_k", "Kai Toedter", "kai@toedter.com");
        User doe_jo = new User(null,"doe_jo", "John Doe", "john@doe.com");
        User doe_ja = new User(null,"doe_ja", "Jane Doe", "jane@doe.com");

        userRepository.saveAll(Arrays.asList(toedter_k, doe_jo, doe_ja));
    }
}