package de.rieckpil.learning.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.rieckpil.learning.entity.Employee;

@Repository
@Transactional
public class EmployeeRepository {

	@PersistenceContext
	EntityManager em;

	public Employee findById(Long id) {
		return em.find(Employee.class, id);
	}

	public Employee save(Employee employee) {
		if (employee.getId() == null) {
			em.persist(employee);
		} else {
			em.merge(employee);
		}
		return employee;
	}

	public List<Employee> retrieveAllEmployees() {
		return em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
	}

}
