package de.rieckpil.learning.injection;

import javax.inject.Inject;

public class SuperService {
	
	@Inject
	DataResolver dataResolver;
	
	public String doFoo() {
		return dataResolver.getData();
	}

}
