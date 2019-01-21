package de.rieckpil.learning.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PostStatusInfo {

	@Id
	private Long id;

	private String name;

	private String description;

	public PostStatusInfo() {
		super();
	}

	public PostStatusInfo(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
