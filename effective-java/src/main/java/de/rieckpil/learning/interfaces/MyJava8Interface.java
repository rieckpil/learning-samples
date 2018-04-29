package de.rieckpil.learning.interfaces;

public interface MyJava8Interface {

    public void doFoo(String foo);

    public default void doBar(String bar) {
        System.out.println(bar);
    }

}
