package de.rieckpil.learning.control;

import de.rieckpil.learning.entity.Color;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

public class DefaultColorExposer {

    @Produces
    @Diesel
    public Color exposeDefaultColor() {
        return Color.BLACK;
    }
}
