package de.rieckpil.learning.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Cacheable
public class Passport {

	@Id
	@GeneratedValue
	private Long id;

	private String number;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "passport")
	@JsonIgnore
	private Student student;

	public Passport() {
		super();
	}

	public Passport(String number) {
		super();
		this.number = number;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
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

	@Override
	public String toString() {
		return "Passport [id=" + id + ", number=" + number + "]";
	}

}
