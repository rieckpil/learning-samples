package de.rieckpil.learning.domain;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class PublishInfo {

	@Id
	@GeneratedValue
	private Long id;
	
	@MapsId
	@OneToOne
	private Post post;
	
	private Instant publishedAt = Instant.now();
	
	private Instant changedAt = Instant.now();
	
	public PublishInfo() {
		
	}

	public PublishInfo(Post post) {
		super();
		this.post = post;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Instant getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Instant publishedAt) {
		this.publishedAt = publishedAt;
	}

	public Instant getChangedAt() {
		return changedAt;
	}

	public void setChangedAt(Instant changedAt) {
		this.changedAt = changedAt;
	}
	
	
}
