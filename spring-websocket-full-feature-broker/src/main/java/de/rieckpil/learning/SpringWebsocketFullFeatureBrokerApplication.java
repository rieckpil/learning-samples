package de.rieckpil.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringWebsocketFullFeatureBrokerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebsocketFullFeatureBrokerApplication.class, args);
	}

}
