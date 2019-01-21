package de.rieckpil.learning.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Post {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@Enumerated(EnumType.ORDINAL)
	private PostStatus status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status", insertable = false, updatable = false)
	private PostStatusInfo statusInfo;

	public Post() {
	}

	public Post(String name, PostStatus status) {
		this.name = name;
		this.status = status;
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

	public PostStatus getStatus() {
		return status;
	}

	public void setStatus(PostStatus status) {
		this.status = status;
	}

	public PostStatusInfo getStatusInfo() {
		return statusInfo;
	}

	public void setStatusInfo(PostStatusInfo statusInfo) {
		this.statusInfo = statusInfo;
	}

}
