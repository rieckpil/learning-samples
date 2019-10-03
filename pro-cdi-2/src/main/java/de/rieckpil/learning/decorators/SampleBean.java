package de.rieckpil.learning.decorators;

public class SampleBean implements SampleBeanInterface{

    @Override
    public String sayHello(String name) {
        return "Hello, " + name;
    }

    @Override
    public String sayGoodbye(String name) {
        return "Goodbye, " + name;
    }
}
