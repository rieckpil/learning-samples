package de.rieckpil.learning.repositories;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import de.rieckpil.learning.entity.Course;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringCourseRepositoryTest {

	@Autowired
	SpringCourseRepository repository;

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Test
	public void testRepository() {
		Optional<Course> course = repository.findById(1000L);
		System.out.println(course.get().getName());
	}

	@Test
	public void sort() {
		Sort sort = new Sort(Sort.Direction.DESC, "name");
		logger.info("All sorted courses: {}", repository.findAll(sort));
	}

	@Test
	public void pagination() {

		PageRequest pageRequest = PageRequest.of(0, 3);

		Page<Course> firstPage = repository.findAll(pageRequest);

		logger.info("First page : {}", firstPage.getContent());

		Pageable secondPageable = firstPage.nextPageable();
		Page<Course> secondPage = repository.findAll(secondPageable);

		logger.info("Second page : {}", secondPage.getContent());
	}

	@Test
	public void customQuery() {
		logger.info("Custom query (LIKE '%28%': {}", repository.courseWith28InName());
		logger.info("Custom query NATIVE (LIKE '%28%': {}", repository.courseWith28InNameUsingNativeQuery());
		logger.info("Custom query NAMED (LIKE '%28%': {}", repository.courseWith28InNameUsingNamedQuery());
	}

}
