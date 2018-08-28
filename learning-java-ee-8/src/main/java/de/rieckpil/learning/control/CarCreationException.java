package de.rieckpil.learning.control;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class CarCreationException extends Exception {

    public CarCreationException(String message) {
        super(message);
    }
}
