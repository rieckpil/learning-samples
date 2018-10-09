package de.rieckpil.learning;

import java.util.logging.Logger;

import javax.inject.Inject;

public class LoggerTestSupport {

	@Inject
	Logger logger;

	public Logger getLog() {
		return logger;
	}

}
