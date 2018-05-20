package de.rieckpil.learning.springboot2book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaBeanConfiguration {

    @Bean
    public Foo foo(@Value("${foo.name:n/a}") String name) {
        return new Foo(name);
    }

}

