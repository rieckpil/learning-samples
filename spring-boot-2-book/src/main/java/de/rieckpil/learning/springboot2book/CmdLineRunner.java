package de.rieckpil.learning.springboot2book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CmdLineRunner implements CommandLineRunner {

    private final Foo foo;
    private final ExampleProperties exampleProperties;

    public CmdLineRunner(Foo foo, ExampleProperties exampleProperties) {
        this.foo = foo;
        this.exampleProperties = exampleProperties;
    }

    @Override
    public void run(String... args) throws Exception {
        foo.sayHello();
        System.out.println(exampleProperties.toString());
    }
}
