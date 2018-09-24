package de.rieckpil.learning.repositories;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.rieckpil.learning.entity.Passport;
import de.rieckpil.learning.entity.Student;

@Repository
@Transactional
public class StudentRepository {

	@PersistenceContext
	EntityManager em;

	public Student findById(Long id) {
		return em.find(Student.class, id);
	}

	public Student save(Student student) {
		if (student.getId() == null) {
			em.persist(student);
		} else {
			em.merge(student);
		}
		return student;
	}

	public void deleteById(Long id) {
		Student Student = em.find(Student.class, id);
		em.remove(Student);
	}

	public void saveStudentWithPassport() {
		Passport p1 = new Passport(UUID.randomUUID().toString());
		em.persist(p1);
		Student student = new Student("Mike");
		student.setPassport(p1);
		em.persist(student);
	}

}
