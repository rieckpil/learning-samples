package de.rieckpil.learning.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import de.rieckpil.learning.MasterHibernateJpaSpringBootApplication;
import de.rieckpil.learning.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MasterHibernateJpaSpringBootApplication.class)
public class CourseRepositoryTest {

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	EntityManager em;

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Test
	public void testFindById() {
		Course course = courseRepository.findById(1000L);

		assertNotNull(course);
		assertEquals("in28Minutes Beginner", course.getName());
	}

	@Test
	@Transactional
	public void testFindById_firstLevelCache() {
		Course course = courseRepository.findById(1000L);

		System.out.println("--- first course retrived");

		Course course2 = courseRepository.findById(1000L);

		assertNotNull(course);
		assertNotNull(course2);
		assertEquals("in28Minutes Beginner", course.getName());
	}

	@Test
	public void testFindById_firstLevelCacheNoTransaction() {
		Course course = courseRepository.findById(1000L);

		System.out.println("--- first course retrived without transaction");

		Course course2 = courseRepository.findById(1000L);

		assertNotNull(course);
		assertNotNull(course2);
		assertEquals("in28Minutes Beginner", course.getName());
	}

	@Test
	@DirtiesContext
	public void testDeleteById() {

		System.out.println("--- Deleting course 1001");

		courseRepository.deleteById(1001L);
		Course course = courseRepository.findById(1001L);
		assertNull(course);
	}

	@Test
	@DirtiesContext
	public void testPlayWithEntityManager() {
		courseRepository.playWithEntityManager();
	}

	@Test
	@Transactional
	@DirtiesContext
	public void nPlusOneProblem() {

		logger.info("---  N+1 issue with JPA");
		List<Course> resultList = em.createNamedQuery("get_all_courses", Course.class).getResultList();
		
		for (Course course : resultList) {
			logger.info("Course {} with Students {}", course, course.getStudents());
		}
	}

	@Test
	@Transactional
	@DirtiesContext
	public void fixNPlusOne_EntityGraph() {

		logger.info("--- Fixing N+1 issue with JPA");

		EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
		entityGraph.addSubgraph("students");

		List<Course> resultList = em.createNamedQuery("get_all_courses", Course.class)
				.setHint("javax.persistence.loadgraph", entityGraph).getResultList();
		for (Course course : resultList) {
			logger.info("Course {} with Students {}", course, course.getStudents());
		}
	}

	@Test
	@Transactional
	@DirtiesContext
	public void fixNPlusOne_JoinFetch() {

		logger.info("--- Fixing N+1 issue with JPA");

		List<Course> resultList = em.createNamedQuery("get_all_courses_join_fetch", Course.class).getResultList();
		for (Course course : resultList) {
			logger.info("Course {} with Students {}", course, course.getStudents());
		}
	}

	@Test
	public void testWithJPQL_CoursesWithoutStudents() {

		TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c WHERE c.students is empty", Course.class);

		List<Course> result = query.getResultList();

		System.out.println(result);
	}

	@Test
	public void testWithJPQL_atLeastTwoStudents() {

		TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c WHERE size(c.students) >= 2", Course.class);

		List<Course> result = query.getResultList();

		System.out.println(result);
	}

	@Test
	public void testWithJPQL_orderBySize() {

		TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c ORDER BY size(c.students) DESC",
				Course.class);

		List<Course> result = query.getResultList();

		System.out.println(result);
	}

	@Test
	public void testWithCriteria_getAllCourses() {
		// SELECT c FROM Course c
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		Root<Course> courseRoot = cq.from(Course.class);
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		System.out.println(resultList);
	}

	@Test
	public void testWithCriteria_getAllCoursesLike() {
		// SELECT c FROM Course c WHERE name like '%28%'
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		Root<Course> courseRoot = cq.from(Course.class);
		Predicate like28 = cb.like(courseRoot.get("name"), "%28%");
		cq.where(like28);

		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		System.out.println(resultList);
	}

	@Test
	public void testWithCriteria_getAllCoursesThatAreEmpty() {
		// SELECT c FROM Course c WHERE c.students is empty
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		Root<Course> courseRoot = cq.from(Course.class);
		Predicate studentsIsEmpty = cb.isEmpty(courseRoot.get("students"));
		cq.where(studentsIsEmpty);

		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		System.out.println(resultList);
	}

	@Test
	public void testWithCriteria_join() {
		// SELECT c FROM Course c JOIN students s
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		Root<Course> courseRoot = cq.from(Course.class);

		Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);

		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		System.out.println(resultList);
	}

}
