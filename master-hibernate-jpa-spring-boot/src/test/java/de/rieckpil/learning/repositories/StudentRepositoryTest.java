package de.rieckpil.learning.repositories;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import de.rieckpil.learning.entity.Course;
import de.rieckpil.learning.entity.Passport;
import de.rieckpil.learning.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepositoryTest {

	@Autowired
	EntityManager em;

	@Test
	@Transactional
	public void someTest() {
		Student student = em.find(Student.class, 20001L);
		Passport passport = student.getPassport();
		passport.setNumber(UUID.randomUUID().toString());
		student.setName("Updated");
	}

	@Test
	public void testWithJPQL_like() {

		TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE s.passport.number like '%1234%'",
				Student.class);

		List<Student> result = query.getResultList();

		System.out.println(result);
	}

	@Test
	public void testWithJPQL_join() {
		Query query = em.createQuery("SELECT c,s FROM Course c JOIN c.students s");
		List<Object[]> resultList = query.getResultList();

		System.out.println(resultList.size());
		for (Object[] result : resultList) {
			System.out.println((Course) result[0]);
			System.out.println((Student) result[1]);
		}
	}

	@Test
	public void testWithJPQL_left_join() {
		Query query = em.createQuery("SELECT c,s FROM Course c LEFT JOIN c.students s");
		List<Object[]> resultList = query.getResultList();

		System.out.println(resultList.size());
		for (Object[] result : resultList) {
			System.out.println((Course) result[0]);
			System.out.println((Student) result[1]);
		}
	}

	@Test
	public void testWithJPQL_cross_join() {
		Query query = em.createQuery("SELECT c,s FROM Course c, Student s");
		List<Object[]> resultList = query.getResultList();

		System.out.println(resultList.size());
		for (Object[] result : resultList) {
			System.out.println((Course) result[0]);
			System.out.println((Student) result[1]);
		}
	}

}
