package de.rieckpil.microprofile.customer.boundary;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import de.rieckpil.microprofile.customer.entity.Adress;
import de.rieckpil.microprofile.customer.entity.Customer;

@ApplicationScoped
public class CustomerStore {

	private List<Customer> customers;

	@Inject
	@ConfigProperty(name = "amount.customers", defaultValue = "20")
	private int amountOfCustomers;
	
	@PostConstruct
	public void init() {

		customers = new ArrayList<Customer>();

		for (int i = 0; i < amountOfCustomers; i++) {
			Customer customer = new Customer("Max", "Mustermann",
					LocalDateTime.of(ThreadLocalRandom.current().nextInt(1950, 2005), 9, 13, 0, 0),
					new Adress(ThreadLocalRandom.current().nextInt(4000, 9800), "Hauptstraße 14", "Herzogenaurach"));
			customers.add(customer);
		}

	}

	public List<Customer> getAllCustomers() {
		
		return customers;
	}

}
