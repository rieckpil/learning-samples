package de.rieckpil.learning.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import de.rieckpil.learning.MasterHibernateJpaSpringBootApplication;
import de.rieckpil.learning.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MasterHibernateJpaSpringBootApplication.class)
public class CourseRepositoryTest {

	@Autowired
	CourseRepository courseRepository;

	@Test
	public void testFindById() {
		Course course = courseRepository.findById(1000L);

		assertNotNull(course);
		assertEquals("in28Minutes", course.getName());
	}
	
	@Test
	@DirtiesContext
	public void testDeleteById() {
		courseRepository.deleteById(1001L);
		Course course = courseRepository.findById(1001L);
		assertNull(course);
	}
	
	@Test
	@DirtiesContext
	public void testPlayWithEntityManager() {
		courseRepository.playWithEntityManager();
	}

}
