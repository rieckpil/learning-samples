package de.rieckpil.learning;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import de.rieckpil.learning.entity.Course;
import de.rieckpil.learning.entity.FullTimeEmployee;
import de.rieckpil.learning.entity.PartTimeEmployee;
import de.rieckpil.learning.entity.Review;
import de.rieckpil.learning.entity.Student;
import de.rieckpil.learning.repositories.CourseRepository;
import de.rieckpil.learning.repositories.EmployeeRepository;
import de.rieckpil.learning.repositories.StudentRepository;

@SpringBootApplication
public class MasterHibernateJpaSpringBootApplication implements CommandLineRunner {

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	TransactionSample transactionSample;

	@PersistenceContext
	EntityManager em;

	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	public static void main(String[] args) {
		SpringApplication.run(MasterHibernateJpaSpringBootApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		Review review1 = new Review("5", "Great Hands-on Stuff.");
		Review review2 = new Review("3", "Good course!");

		List<Review> reviews = new ArrayList<>();
		reviews.add(review1);
		reviews.add(review2);

		addReviewsToCourse(1002L, reviews);

		Student s1 = studentRepository.findById(20001L);
		System.out.println(s1.getCourses());

		insertStudentAndCourse();

		employeeRepository.save(new FullTimeEmployee("Jack", new BigDecimal("10000")));
		employeeRepository.save(new PartTimeEmployee("Jill", new BigDecimal("50")));

		logger.info("All employees: {} ", employeeRepository.retrieveAllEmployees());

		transactionSample.doSomething();
	}

	private void addReviewsToCourse(Long courseId, List<Review> reviews) {
		Course course = courseRepository.findById(courseId);
		logger.info("Reviews of Course 1003 -> {}", course.getReviews());
		for (Review review : reviews) {
			course.addReview(review);
			review.setCourse(course);
			em.persist(review);
		}
	}

	private void insertStudentAndCourse() {

		Student student = new Student("Jack");
		Course course = new Course("k8s in 100 steps!");

		em.persist(student);
		em.persist(course);

		student.addCourse(course);
		course.addStudent(student);

		em.persist(student);
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
