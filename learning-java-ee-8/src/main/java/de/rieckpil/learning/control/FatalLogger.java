package de.rieckpil.learning.control;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FatalLogger {

    private static final Logger LOGGER = Logger.getLogger(FatalLogger.class.getName());

    public void fatal(Exception e) {
        LOGGER.log(Level.SEVERE, e.getMessage());
    }
}
