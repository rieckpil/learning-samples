package de.rieckpil.learning.repositories;

import java.util.UUID;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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

}
