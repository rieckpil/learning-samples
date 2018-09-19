package de.rieckpil.learning.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.rieckpil.learning.entity.Course;

@Repository
@Transactional
public class CourseRepository {

	@PersistenceContext
	EntityManager em;

	public Course findById(Long id) {
		return em.find(Course.class, id);
	}

	public Course save(Course course) {
		return em.merge(course);
	}

	public void deleteById(Long id) {
		Course course = em.find(Course.class, id);
		em.remove(course);
	}

}
