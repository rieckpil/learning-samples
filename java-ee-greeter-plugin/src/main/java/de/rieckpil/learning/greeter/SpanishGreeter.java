package de.rieckpil.learning.greeter;

import de.rieckpil.api.greeter.Greeter;

public class SpanishGreeter implements Greeter {

    @Override
    public String greetPerson(String s) {
        return "Olâ: " + s;
    }
}
