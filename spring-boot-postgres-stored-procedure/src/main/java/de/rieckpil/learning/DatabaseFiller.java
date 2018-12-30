package de.rieckpil.learning;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class DatabaseFiller {

	@Autowired
	private UserRepository repository;

	@EventListener(ApplicationReadyEvent.class)
	public void init() {

		for (int i = 0; i < 100; i++) {
			repository.save(new User("Duke", UUID.randomUUID().toString()));
		}

	}

}
