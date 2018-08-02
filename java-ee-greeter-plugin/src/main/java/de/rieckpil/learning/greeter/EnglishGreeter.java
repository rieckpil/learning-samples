package de.rieckpil.learning.greeter;

import de.rieckpil.api.greeter.Greeter;

public class EnglishGreeter implements Greeter {

    @Override
    public String greetPerson(String s) {
        return "Hello: " + s;
    }
}
