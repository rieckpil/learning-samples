package de.rieckpil.learning.jaxworkshop;

import org.junit.Test;

import static org.junit.Assert.*;

public class HelloWorldTest {

    public static final String NAME = "Hallo Welt!";

    @Test
    public void testEntity() {

        HelloWorld cut = new HelloWorld();

        cut.setName(NAME);

        assertEquals(NAME, cut.getName());
    }

    @Test
    public void testAllArgsConstructor() {

        String name = "Jax 2018!";

        HelloWorld cut = new HelloWorld(name);

        assertEquals(name, cut.getName());
    }

}