package de.rieckpil.learning.springboot2book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CmdLineRunner implements CommandLineRunner {

    private final Foo foo;

    public CmdLineRunner(Foo foo) {
        this.foo = foo;
    }

    @Override
    public void run(String... args) throws Exception {
        foo.sayHello();
    }
}
