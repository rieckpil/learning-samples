package de.rieckpil.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import de.rieckpil.learning.entity.Course;
import de.rieckpil.learning.entity.Student;
import de.rieckpil.learning.repositories.CourseRepository;
import de.rieckpil.learning.repositories.StudentRepository;

@SpringBootApplication
public class MasterHibernateJpaSpringBootApplication implements CommandLineRunner {

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(MasterHibernateJpaSpringBootApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		studentRepository.saveStudentWithPassport();

		Student student = studentRepository.findById(2L);

		System.out.println(student.getPassport());

	}

	private void insertStuff() {
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
