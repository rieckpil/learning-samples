package de.rieckpil.learning.repositories;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.rieckpil.learning.entity.Course;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringCourseRepositoryTest {

	@Autowired
	SpringCourseRepository repository;
	
	@Test
	public void testRepository() {
		
		Optional<Course> course = repository.findById(1000L);
		
		System.out.println(course.get().getName());
		
	}
	
}
