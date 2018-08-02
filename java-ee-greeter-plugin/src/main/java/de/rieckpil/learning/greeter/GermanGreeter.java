package de.rieckpil.learning.greeter;

import de.rieckpil.api.greeter.Greeter;

public class GermanGreeter implements Greeter {

    @Override
    public String greetPerson(String s) {
        return "Hallo: " + s;
    }
}
