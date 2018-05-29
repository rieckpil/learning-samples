package de.rieckpil.learning.springboot2book;

import de.rieckpil.learning.springboot2book.entities.Person;
import de.rieckpil.learning.springboot2book.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class CmdLineRunner implements CommandLineRunner {

    private final Foo foo;
    private final ExampleProperties exampleProperties;
    private final PersonRepository personRepository;
    private final ExternalSystemCallService externalSystemCallService;

    public CmdLineRunner(Foo foo, ExampleProperties exampleProperties, PersonRepository personRepository, ExternalSystemCallService externalSystemCallService) {
        this.foo = foo;
        this.exampleProperties = exampleProperties;
        this.personRepository = personRepository;
        this.externalSystemCallService = externalSystemCallService;
    }

    @Override
    public void run(String... args) throws Exception {
        foo.sayHello();
        System.out.println(exampleProperties.toString());

        for (int i = 0; i < 100; i++) {
            personRepository.save(createPerson());
        }

        System.out.println("External system call: " + externalSystemCallService.getRemoteInfo());

    }

    private Person createPerson() {
        Person p = new Person();
        p.setAge(ThreadLocalRandom.current().nextInt(10,80));
        p.setLastName(UUID.randomUUID().toString());
        return p;
    }
}
