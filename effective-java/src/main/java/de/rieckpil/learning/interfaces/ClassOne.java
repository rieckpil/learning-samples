package de.rieckpil.learning.interfaces;

public class ClassOne implements MyJava8Interface {

    @Override
    public void doFoo(String foo) {
        System.out.println("foo is: " + foo);
    }
}
