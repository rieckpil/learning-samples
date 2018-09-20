package de.rieckpil.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.rieckpil.learning.entity.Course;
import de.rieckpil.learning.repositories.CourseRepository;

@SpringBootApplication
public class MasterHibernateJpaSpringBootApplication implements CommandLineRunner {

	@Autowired
	CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(MasterHibernateJpaSpringBootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Course course = new Course("Mathe I");
		courseRepository.save(course);
		course.setName("No Mathe");
		courseRepository.save(course);
		courseRepository.save(new Course("Mathe II"));
		courseRepository.save(new Course("Datenbanken"));
		courseRepository.save(new Course("Webprogrammierung"));

		System.out.println(courseRepository.findById(1L));

	}
}
