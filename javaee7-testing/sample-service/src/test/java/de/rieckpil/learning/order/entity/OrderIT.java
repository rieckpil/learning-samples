package de.rieckpil.learning.order.entity;

import org.junit.Rule;
import org.junit.Test;

public class OrderIT {

	@Rule
	public EntityManagerProvider provider = EntityManagerProvider.withUnit("it");

	@Test
	public void crud() {
		this.provider.tx().begin();
		this.provider.em().merge(new Order("42"));
		System.out.println("sucessfully stored the order 42");
		this.provider.tx().commit();
	}
}
