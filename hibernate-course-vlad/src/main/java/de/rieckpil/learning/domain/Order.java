package de.rieckpil.learning.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "sequence", allocationSize = 20, initialValue = 1000)
	private Long id;

	private String number;

	public Order(String number) {
		this.number = number;
	}

	public Order() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}