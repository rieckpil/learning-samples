package de.rieckpil.learning.injection;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class LoggerProducer {

	@Produces
	public Logger expose(InjectionPoint ip) {
		Class<?> clazz = ip.getMember().getDeclaringClass();
		return Logger.getLogger(clazz.getName());
	}

}
