package de.rieckpil.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringThymeleafProcessingApplication implements CommandLineRunner {

	@Autowired
	TemplateProcessor templateProcessor;

	public static void main(String[] args) {
		SpringApplication.run(SpringThymeleafProcessingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		templateProcessor.processTemplate();
		System.exit(0);
	}
}
