package de.rieckpil.learning.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

@Entity
@TypeDefs({ @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class),
		@TypeDef(name = "json", typeClass = JsonStringType.class) })
public class Post {

	@Id
	@GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "sequence", allocationSize = 20, initialValue = 1000)
	private Long id;

	private String name;

	@OneToOne(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private PublishInfo publishInfo;

	@Type(type = "jsonb")
	@Column(columnDefinition = "jsonb")
	private Content content;

	@Enumerated(EnumType.ORDINAL)
	private PostStatus status;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.PERSIST })
	@JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<Tag> tags = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status", insertable = false, updatable = false)
	private PostStatusInfo statusInfo;

	public Post() {
	}

	public Post(String name, PostStatus status, Content content) {
		this.name = name;
		this.status = status;
		this.content = content;
	}

	public Post(String name, PostStatus status, Content content, PublishInfo publishInfo) {
		this.name = name;
		this.status = status;
		this.content = content;
		this.publishInfo = publishInfo;
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

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public PublishInfo getPublishInfo() {
		return publishInfo;
	}

	public void setPublishInfo(PublishInfo publishInfo) {
		this.publishInfo = publishInfo;
		publishInfo.setPost(this);
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public void addTag(Tag tag) {
		this.tags.add(tag);
	}

}
