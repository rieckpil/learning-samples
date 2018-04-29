package de.rieckpil.learning.interfaces;

public class ClassTwo implements MyJava8Interface {

    @Override
    public void doFoo(String foo) {
        System.out.println(foo + foo);
    }

    @Override
    public void doBar(String bar) {
        System.out.println(bar.toUpperCase());
    }
}
