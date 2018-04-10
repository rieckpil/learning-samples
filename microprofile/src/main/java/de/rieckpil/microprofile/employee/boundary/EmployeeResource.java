package de.rieckpil.microprofile.employee.boundary;

import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Metric;

@Path("/employees")
@ApplicationScoped
public class EmployeeResource {

	private ArrayList<String> employees = new ArrayList<String>();

	@Inject
	@Metric
	Counter employeeCount;

	@PostConstruct
	private void init() {
		// initialise counter with beginning number
		employees.addAll(Arrays.asList("Max", "Paul", "Tom", "Bill"));
		employeeCount.inc(4);
	}

	@GET
	@Path("{id}")
	public String getEmployeeById(@PathParam("id") int id) {
		// return specified employee by id via path parameter
		return employees.get(id);
	}

	@GET
	public String getAllEmployees() throws InterruptedException {
		// return all employees without parameters
		return employees.toString();
	}

	@POST
	@Metered
	public void createEmployee(@FormParam("name") String emp) {
		// add an employee from form parameter and increment "employeeCount"
		if (employees.add(emp))
			employeeCount.inc();
	}

	@DELETE
	@Path("{id}")
	@Metered
	public void deleteEmployee(@PathParam("id") int id) {
		// delete an employee by id via path parameter, and decrement "employeeCount"
		if (id >= employees.size()) {
			return;
		} else {
			employees.remove(id);
			employeeCount.dec();
		}
	}
}
