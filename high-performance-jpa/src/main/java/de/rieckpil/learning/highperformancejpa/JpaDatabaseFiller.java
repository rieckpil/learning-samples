package de.rieckpil.learning.highperformancejpa;

import de.rieckpil.learning.highperformancejpa.entity.Person;
import de.rieckpil.learning.highperformancejpa.entity.Post;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JpaDatabaseFiller implements CommandLineRunner {

    private final PersonRepository personRepository;
    private final PostRepository postRepository;

    public JpaDatabaseFiller(PersonRepository personRepository, PostRepository postRepository) {
        this.personRepository = personRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) {

        Person p1 = new Person();
        p1.setName("Tommy");

        personRepository.save(p1);

        Post post = new Post();
        postRepository.save(post);

    }
}
