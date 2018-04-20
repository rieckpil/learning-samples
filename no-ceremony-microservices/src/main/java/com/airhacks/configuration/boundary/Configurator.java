package com.airhacks.configuration.boundary;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class Configurator {

	@Produces
	public String expose(InjectionPoint ip) {
		String key = ip.getMember().getName();
		return System.getenv().getOrDefault(key, System.getProperty(key, "NO PROPERTY SET"));
	}

}
