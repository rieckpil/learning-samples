package de.rieckpil.learning;

import org.springframework.beans.factory.annotation.Value;

public interface NamesOnly {
	
	@Value("#{target.firstName + ' ' + target.lastName}")
	String getFullName();
}
