package de.rieckpil.learning.repositories;

import de.rieckpil.learning.entity.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class CourseRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@PersistenceContext
	EntityManager em;

	public Course findById(Long id) {
		return em.find(Course.class, id);
	}

	public Course save(Course course) {
		if (course.getId() == null) {
			em.persist(course);
		} else {
			em.merge(course);
		}
		return course;
	}

	public void deleteById(Long id) {
		Course course = em.find(Course.class, id);
		em.remove(course);
	}

	public void playWithEntityManager() {
		logger.info("--- Starting to play with the EntityManager");

		Course course1 = new Course("Web Services");
		em.persist(course1);
		Course course2 = new Course("React");
		em.persist(course2);

		em.flush();

		// em.clear();
		// em.detach(course2);

		Course extracted = em.find(Course.class, 1L);

		course1.setName("Updated - Web Services");
		course2.setName("Updated - React");

		em.refresh(course1);

		em.flush();
	}

	public void queryWithJPQL() {

		List courseUnTyped = em.createQuery("SELECT c FROM Course c").getResultList();
		List<Course> courses = em.createQuery("SELECT c FROM Course c", Course.class).getResultList();
		List<Course> coursesWhere = em.createQuery("SELECT c FROM Course c WHERE name like 'in%'", Course.class)
				.getResultList();

		for (Course course : coursesWhere) {
			System.out.println(course);
		}

		List<Course> get_all_courses = em.createNamedQuery("get_all_courses", Course.class).getResultList();

		for (Course course : get_all_courses) {
			System.out.println(course);
		}

	}

	public void nativeQuery() {

		Query query = em.createNativeQuery("SELECT * FROM course_details WHERE id = ? AND name = :name", Course.class);
		query.setParameter("name", "in28Minutes Advanced");
		query.setParameter(1, 1001);

		List<Course> resultList = query.getResultList();

		for (Course course : resultList) {
			System.out.println(course);
		}

		int rowsUpdated = em.createNativeQuery("UPDATE course_details SET last_updated_date = sysdate()")
				.executeUpdate();

		System.out.println("rowsUpdated = " + rowsUpdated);

		Query q2 = em.createNativeQuery("SELECT name FROM course_details");

		List nativeResult = q2.getResultList();

		for (Object name : nativeResult) {

			String columnResult = (String) name;
			System.out.println("--- " + columnResult);

		}

	}

	public List<Course> findAll() {
		return em.createQuery("SELECT c FROM Course c", Course.class).getResultList();
	}

}
