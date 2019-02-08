package de.rieckpil.learning;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@Entity
@Table(name = "application_users")
@NamedStoredProcedureQueries({ @NamedStoredProcedureQuery(name = "inc", procedureName = "inc", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "inParam1", type = Integer.class) }) })
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	private String name;

	private String userId;

	public User() {
	}

	public User(String name, String identifier) {
		super();
		this.name = name;
		this.userId = identifier;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
