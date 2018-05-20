package de.rieckpil.learning.springboot2book;

public class Foo {

    private String name;

    public Foo(String name) {
        this.name = name;
    }

    public void sayHello() {
        System.out.println(name);
    }
}
