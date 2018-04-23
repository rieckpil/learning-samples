package de.rieckpil.microprofile.customer.boundary;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import de.rieckpil.microprofile.customer.entity.Adress;
import de.rieckpil.microprofile.customer.entity.Customer;

@ApplicationScoped
public class CustomerStore {

	private List<Customer> customers;

	@PostConstruct
	public void init() {
		
		customers = new ArrayList<Customer>();
		
		Customer max = new Customer("Max", "Mustermann", LocalDateTime.of(1995, 9, 13, 0, 0),
				new Adress(91074, "Hauptstraße 14", "Herzogenaurach"));
		Customer tom = new Customer("Tom", "Meier", LocalDateTime.of(1990, 9, 13, 0, 0),
				new Adress(91084, "Hauptstraße 14", "Erlangen"));
		Customer frank = new Customer("Frank", "Schuster", LocalDateTime.of(1996, 9, 15, 0, 0),
				new Adress(91084, "Am Buck 13", "Erlangen"));

		customers.addAll(Arrays.asList(max, tom, frank));

	}

	public List<Customer> getAllCustomers() {
		return customers;
	}

}
