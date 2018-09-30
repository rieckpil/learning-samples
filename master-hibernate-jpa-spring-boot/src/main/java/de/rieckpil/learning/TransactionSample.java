package de.rieckpil.learning;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import de.rieckpil.learning.entity.Course;

@Service
public class TransactionSample {

	@Autowired
	EntityManager em;

	private final Logger log = LoggerFactory.getLogger(TransactionSample.class);

	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void doSomething() {
		System.out.println("Accessing multiple resources in one transaction");
		List<Course> allCourses = em.createQuery("SELECT c FROM Course c", Course.class).getResultList();
		log.info("All courses in READ_COMMITED transaction: {}", allCourses);
	}

}
