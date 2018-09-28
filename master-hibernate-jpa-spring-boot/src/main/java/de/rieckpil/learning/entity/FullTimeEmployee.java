package de.rieckpil.learning.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class FullTimeEmployee extends Employee {

	private BigDecimal salaray;

	protected FullTimeEmployee() {
	}

	public FullTimeEmployee(String name, BigDecimal salaray) {
		super(name);
		this.salaray = salaray;
	}

	public BigDecimal getSalary() {
		return salaray;
	}

	public void setSalary(BigDecimal salaray) {
		this.salaray = salaray;
	}

}
