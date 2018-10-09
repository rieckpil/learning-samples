package de.rieckpil.learning.injection;

import java.util.logging.Logger;

import javax.inject.Inject;

public class SuperService {

	@Inject
	DataResolver dataResolver;

	@Inject
	Logger LOG;

	public String doFoo() {
		LOG.info("about to get data");
		return dataResolver.getData();
	}

}
