package de.rieckpil.learning.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER)
	private List<Item> items = new ArrayList<>();

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

	public List<Item> getItems() {
		return items;
	}

	public void addItem(Item item) {
		item.setOrder(this);
		this.items.add(item);
	}

}